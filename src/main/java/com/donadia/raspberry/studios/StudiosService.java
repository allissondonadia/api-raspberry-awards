package com.donadia.raspberry.studios;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.donadia.raspberry.utils.StringUtils;

@Service
public class StudiosService {
    
    private final StudiosRepository studiosRepository;

    @Autowired
    public StudiosService(StudiosRepository studiosRepository) {
        this.studiosRepository = studiosRepository;
    }

    public Optional<Studio> findById(Long id) {
        return studiosRepository.findById(id);
    }

    public Page<Studio> findAll(Pageable pageable) {
        return studiosRepository.findAll(pageable);
    }

    public Set<Studio> createStudios(String studiosNames) {
        Set<String> studios = StringUtils.splitCSVString(studiosNames);
        Set<Studio> studiosSet = new HashSet<>();
        for (String studio : studios) {            
            studiosSet.add(findOrCreateStudio(studio));
        }
        return studiosSet;
    }

    public Studio findOrCreateStudio(String name) {
        Studio studio = studiosRepository.findByName(name);
        if(studio != null) {
            return studio;
        }
        studio = studiosRepository.save(new Studio(name));
        return studio;
    }
}
