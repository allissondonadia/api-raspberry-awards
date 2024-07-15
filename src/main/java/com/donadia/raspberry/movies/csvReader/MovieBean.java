package com.donadia.raspberry.movies.csvReader;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class MovieBean {
    
    @CsvBindByName
    private int year;

    @CsvBindByName
    private String title;

    @CsvBindByName
    private String studios;

    @CsvBindByName
    private String producers;

    @CsvBindByName
    private String winner;

    public boolean isWinner() {
        return winner.toLowerCase().equals("yes");
    }
}
