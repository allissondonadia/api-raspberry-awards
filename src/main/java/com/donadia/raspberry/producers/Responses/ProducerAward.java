package com.donadia.raspberry.producers.Responses;

import lombok.Data;

@Data
public class ProducerAward {
    
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;
    
    public ProducerAward() {
    }
    
    public ProducerAward(String producer, Integer interval, Integer previousWin, Integer followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }
}
