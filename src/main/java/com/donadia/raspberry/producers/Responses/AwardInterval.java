package com.donadia.raspberry.producers.Responses;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AwardInterval {
    
    private List<ProducerAward> min = new ArrayList<>();
    private List<ProducerAward> max = new ArrayList<>();
    
    public AwardInterval() { }
}
