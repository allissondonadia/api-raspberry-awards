package com.donadia.raspberry.studios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudiosRepository extends JpaRepository<Studio, Long> {

    Studio findByName(String name);    
}