package com.donadia.raspberry.producers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.donadia.raspberry.producers.Responses.Award;

@Repository
public interface ProducersRepository extends JpaRepository<Producer, Long> {

    Producer findByName(String name);   
    
    @Query(value = "SELECT new com.donadia.raspberry.producers.Responses.Award(p.name, m.year) " +
    " FROM Producer p inner join p.movies m " +
    " WHERE m.winner = true " +
    " ORDER BY p.name, m.year")
    List<Award> getAwards();

}
