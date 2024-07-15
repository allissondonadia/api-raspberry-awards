package com.donadia.raspberry.movies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // Add this import statement
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donadia.raspberry.movies.responses.MovieResponse;

@RestController
@RequestMapping("/movies")
public class MoviesController {
 
    private final MoviesService producersService;

    @Autowired
    public MoviesController(MoviesService producersService) {
        this.producersService = producersService;
    }

    @GetMapping("/{id}")
    public Optional<MovieResponse> findOne(@PathVariable("id") Long id) {
        return producersService.findById(id).isPresent() ? Optional.of(producersService.findById(id).get().toResponse()) : Optional.empty();
    }

    @GetMapping()
    public Page<MovieResponse> findAll(@PageableDefault(page = 0, size = 10)
                                @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)})
                                Pageable pageable) {
        return producersService.findAll(pageable).map((movie) -> movie.toResponse());
    }

}
