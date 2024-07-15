package com.donadia.raspberry.movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {

    Movie findByTitle(String title);    
}