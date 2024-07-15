package com.donadia.raspberry.producers.Responses;

import lombok.Data;

@Data
public class Award {
    
    private String producer;
    private Integer year;
    
    public Award() {
    }
    
    public Award(String producer, Integer year) {
        this.producer = producer;
        this.year = year;
    }
}
