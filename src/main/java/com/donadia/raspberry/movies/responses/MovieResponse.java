package com.donadia.raspberry.movies.responses;

import java.util.List;

import lombok.Data;

@Data
public class MovieResponse {
    
    private Integer id;
    private Integer year;
    private String title;
    private boolean winner;

    private List<String> studios;
    private List<String> producers;
    
    public MovieResponse() {
    }
    
    public MovieResponse(Integer id, Integer year, String title, boolean winner, List<String> studios, List<String> producers) {
        this.id = id;
        this.year = year;
        this.title = title;
        this.winner = winner;
        this.studios = studios;
        this.producers = producers;
    }
}
