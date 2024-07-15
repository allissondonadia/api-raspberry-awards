package com.donadia.raspberry.studios;

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
@Table(name="STUDIOS")
public class Studio {
    
    @Getter
    @Id @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "studios")
    private Set<Movie> movies = new HashSet<>();

    public Studio() {
    }

    public Studio(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
    