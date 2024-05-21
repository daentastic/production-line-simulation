package de.dabr.prodlinesim.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import de.dabr.prodlinesim.model.Station;
import de.dabr.prodlinesim.repository.StationRepository;
import jakarta.annotation.PostConstruct;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }
    
    @PostConstruct
    public void createDefaultStation() {
        stationRepository.saveAll(Arrays.asList(
            new Station("Default One"),
            new Station("Default Two")
        ));
    }

}
