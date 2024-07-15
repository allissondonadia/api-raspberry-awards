package com.donadia.raspberry.producers;

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

import com.donadia.raspberry.producers.Responses.AwardInterval;

@RestController
@RequestMapping("/producers")
public class ProducersController {
 
    private final ProducersService producersService;

    @Autowired
    public ProducersController(ProducersService producersService) {
        this.producersService = producersService;
    }

    @GetMapping("/{id}")
    public Optional<Producer> findOne(@PathVariable("id") Long id) {
        return producersService.findById(id);
    }

    @GetMapping()
    public Page<Producer> findAll(@PageableDefault(page = 0, size = 10)
                                @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)})
                                Pageable pageable) {
        return producersService.findAll(pageable);
    }

    @GetMapping("/award-interval")
    public AwardInterval awardInterval() {
        return producersService.getAwardInterval();
    }
}
