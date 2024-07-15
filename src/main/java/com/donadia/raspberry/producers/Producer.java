package com.donadia.raspberry.producers;

import java.util.HashSet;
import java.util.Set;

import com.donadia.raspberry.movies.Movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="PRODUCERS")
public class Producer {
    
    @Getter
    @Id @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "producers")
    private Set<Movie> movies = new HashSet<>();
    
    public Producer() {
    }
    
    public Producer(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Producer{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
