package com.donadia.raspberry.movies;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.donadia.raspberry.movies.responses.MovieResponse;
import com.donadia.raspberry.producers.Producer;
import com.donadia.raspberry.studios.Studio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="MOVIES")
public class Movie {

    @Id @GeneratedValue
    private Integer id;
    
    @Column(name="movie_year")
    private Integer year;

    private String title;

    @ManyToMany
    @JoinTable(name="MOVIES_STUDIOS",
    		   joinColumns=@JoinColumn(name="MOVIE_ID"),
    		   inverseJoinColumns=@JoinColumn(name="STUDIO_ID"))
    private Set<Studio> studios = new HashSet<>();

    @ManyToMany
    @JoinTable(name="MOVIES_PRODUCERS",
    		   joinColumns=@JoinColumn(name="MOVIE_ID"),
    		   inverseJoinColumns=@JoinColumn(name="PRODUCER_ID"))
    private Set<Producer> producers = new HashSet<>();

    @Getter
    @Setter
    private boolean winner;

    public Movie() {
    }

    public Movie(Integer year, String title, Set<Studio> studios, Set<Producer> producers, boolean winner) {
        this.year = year;
        this.title = title;
        this.studios = studios;
        this.producers = producers;
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "year='" + year + '\'' +
                ", title='" + title + '\'' +
                ", studios='" + studios + '\'' +
                ", producers='" + producers + '\'' +
                ", winner=" + winner +
                '}';
    }

    public MovieResponse toResponse() {
        List<String> studiosList = studios.stream().map(Studio::getName).toList();
        List<String> producersList = producers.stream().map(Producer::getName).toList();
        return new MovieResponse(id, year, title, winner, studiosList, producersList);
    }
    
}
