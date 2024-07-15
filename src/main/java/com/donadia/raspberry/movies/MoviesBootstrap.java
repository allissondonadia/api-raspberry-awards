package com.donadia.raspberry.movies;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.donadia.raspberry.movies.csvReader.MovieBean;
import com.donadia.raspberry.producers.Producer;
import com.donadia.raspberry.producers.ProducersService;
import com.donadia.raspberry.studios.Studio;
import com.donadia.raspberry.studios.StudiosService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Component
public class MoviesBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(MoviesBootstrap.class);

    private final StudiosService studiosService;
    private final ProducersService producersService;
    private final MoviesService moviesService;

    @Autowired
    public MoviesBootstrap(StudiosService studiosService, ProducersService producersService, MoviesService moviesService) {
        this.studiosService = studiosService;
        this.producersService = producersService;
        this.moviesService = moviesService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("Producers are up and running!");        

        try {
            importCSVMoviesBean("src/main/resources/movielist.csv");
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void importCSVMoviesBean(String fileName) throws CsvValidationException, IOException {
        List<MovieBean> movieBeans = new CsvToBeanBuilder<MovieBean>(new FileReader(fileName))
                                                            .withType(MovieBean.class)
                                                            .withSeparator(';')
                                                            .build()
                                                            .parse();
        
        for (MovieBean bean : movieBeans) {
            log.info(bean.toString());
            Set<Studio> studios = studiosService.createStudios(bean.getStudios());
            Set<Producer> producers = producersService.createProducers(bean.getProducers());
            Movie movie = new Movie(bean.getYear(), bean.getTitle(), studios, producers, bean.isWinner());
            moviesService.save(movie);            
        }
    }
}