package com.donadia.raspberry.producers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.donadia.raspberry.producers.Responses.Award;
import com.donadia.raspberry.producers.Responses.AwardInterval;
import com.donadia.raspberry.producers.Responses.ProducerAward;
import com.donadia.raspberry.utils.StringUtils;

@Service
public class ProducersService {
    
    private static final Logger log = LoggerFactory.getLogger(ProducersService.class);

    private final ProducersRepository producersRepository;

    @Autowired
    public ProducersService(ProducersRepository producersRepository) {
        this.producersRepository = producersRepository;
    }

    public Optional<Producer> findById(Long id) {
        return producersRepository.findById(id);
    }

    public Page<Producer> findAll(Pageable pageable) {
        //Sort.by(Sort.Direction.ASC, "name")
        return producersRepository.findAll(pageable);
    }

    public Set<Producer> createProducers(String producersNames) {
        Set<String> producers = StringUtils.splitCSVString(producersNames);
        Set<Producer> producersSet = new HashSet<>();
        for (String studio : producers) {            
            producersSet.add(findOrCreateProducer(studio));
        }
        return producersSet;
    }

    public Producer findOrCreateProducer(String name) {
        Producer producer = producersRepository.findByName(name);
        if(producer != null) {
            return producer;
        }
        producer = producersRepository.save(new Producer(name));
        log.info("Creating producer: " + producer);
        return producer;
    }

    public AwardInterval getAwardInterval() {
        Map<String, List<Integer>> producerAwardsMap = new HashMap<>();
        for(Award award : producersRepository.getAwards()) {
            if(!producerAwardsMap.containsKey(award.getProducer())) {
                producerAwardsMap.put(award.getProducer(), new ArrayList<>());
            }
            producerAwardsMap.get(award.getProducer()).add(award.getYear());
        }        

        List<ProducerAward> producerAwardsList = new ArrayList<>();
        producerAwardsMap.forEach((producer, years) -> {
            if(years.size() > 1) {
                years.sort(null);
                log.info("producerAwards: " + producer + " - " + years);
                for(int i = 0; i < years.size() - 1; i++) {
                    for(int j = i + 1; j < years.size(); j++) {
                        producerAwardsList.add(new ProducerAward(producer, years.get(j) - years.get(i), years.get(i), years.get(j)));
                    }
                }
            }
        });
        log.info("producerAwardsList: " + producerAwardsList);

        AwardInterval awardInterval = new AwardInterval();
        Integer minInterval = Integer.MAX_VALUE;
        Integer maxInterval = Integer.MIN_VALUE;
        for (ProducerAward award : producerAwardsList) {
            if(award.getInterval() == minInterval) {
                awardInterval.getMin().add(award);
            } else if(award.getInterval() < minInterval) {
                minInterval = award.getInterval();
                awardInterval.getMin().clear();
                awardInterval.getMin().add(award);
            }

            if(award.getInterval() == maxInterval) {
                awardInterval.getMax().add(award);
            } else if(award.getInterval() > maxInterval) {
                maxInterval = award.getInterval();
                awardInterval.getMax().clear();
                awardInterval.getMax().add(award);
            }
        }

        return awardInterval;
    }
}
