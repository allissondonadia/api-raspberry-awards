package com.donadia.raspberry.producers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.donadia.raspberry.producers.Responses.Award;
import com.donadia.raspberry.producers.Responses.AwardInterval;
import com.donadia.raspberry.producers.Responses.ProducerAward;

public class ProducersServiceTest {

    private ProducersRepository producersRepository;
    private ProducersService producersService;

    private List<Award> awards = new ArrayList<>();
    ProducerAward joelSilver = new ProducerAward("Joel Silver", 1, 1990, 1991);
    ProducerAward matthewVaughn = new ProducerAward("Matthew Vaughn", 13, 2002, 2015);

    @BeforeEach
    public void setup() {
        producersRepository = mock(ProducersRepository.class);
        producersService = new ProducersService(producersRepository);

        awards.clear();
        awards.add(new Award("Producer 1", 2008));
        awards.add(new Award("Producer 1", 2010));

        awards.add(new Award("Producer 2", 2000));
        awards.add(new Award("Producer 2", 2010));
    }

    @Test
    public void testWhenReturnOneItemInMinAndMax() {

        when(producersRepository.getAwards()).thenReturn(awards);

        AwardInterval actualInterval = producersService.getAwardInterval();

        assertEquals(1, actualInterval.getMin().size());
        assertEquals(1, actualInterval.getMax().size());
    }

    @Test
    public void testWhenReturnTwoItemsInMinAndMax() {

        awards.add(new Award("Producer 1", 2018));
        awards.add(new Award("Producer 2", 2002));
        when(producersRepository.getAwards()).thenReturn(awards);

        AwardInterval actualInterval = producersService.getAwardInterval();

        assertEquals(2, actualInterval.getMin().size());
        assertEquals(2, actualInterval.getMax().size());
    }

    @Test
    public void testWhenReturnExactProducers() {
        awards.add(new Award("Joel Silver", 1990));
        awards.add(new Award("Joel Silver", 1991));

        awards.add(new Award("Matthew Vaughn", 2002));
        awards.add(new Award("Matthew Vaughn", 2015));

        when(producersRepository.getAwards()).thenReturn(awards);

        AwardInterval actualInterval = producersService.getAwardInterval();

        assertEquals(joelSilver, actualInterval.getMin().get(0));
        assertEquals(matthewVaughn, actualInterval.getMax().get(0));
    }

    @Test
    public void testWhenAnyoneWinAnything() {

        awards.clear();
        when(producersRepository.getAwards()).thenReturn(awards);

        AwardInterval actualInterval = producersService.getAwardInterval();

        assertEquals(0, actualInterval.getMin().size());
        assertEquals(0, actualInterval.getMax().size());
    }

    @Test
    public void testWhenAnyoneWon2AwardsYet() {

        awards.clear();
        awards.add(new Award("Joel Silver", 1990));
        awards.add(new Award("Matthew Vaughn", 2002));

        when(producersRepository.getAwards()).thenReturn(awards);

        AwardInterval actualInterval = producersService.getAwardInterval();

        assertEquals(0, actualInterval.getMin().size());
        assertEquals(0, actualInterval.getMax().size());
    }
}