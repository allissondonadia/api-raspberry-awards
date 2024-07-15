package com.donadia.raspberry.studios;

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

@RestController
@RequestMapping("/studios")
public class StudiosController {
 
    private final StudiosService studiosService;

    @Autowired
    public StudiosController(StudiosService studiosService) {
        this.studiosService = studiosService;
    }

    @GetMapping("/{id}")
    public Optional<Studio> findOne(@PathVariable("id") Long id) {
        return studiosService.findById(id);
    }

    @GetMapping()
    public Page<Studio> findAll(@PageableDefault(page = 0, size = 10)
                                @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.ASC)})
                                Pageable pageable) {
        return studiosService.findAll(pageable);
    }
}
