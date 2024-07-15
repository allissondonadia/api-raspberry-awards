package com.donadia.raspberry.movies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MoviesService {
    
    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public Movie save(Movie movie) {
        return moviesRepository.save(movie);
    }

    public Optional<Movie> findById(Long id) {
        return moviesRepository.findById(id);
    }

    public Page<Movie> findAll(Pageable pageable) {
        return moviesRepository.findAll(pageable);
    }
}
