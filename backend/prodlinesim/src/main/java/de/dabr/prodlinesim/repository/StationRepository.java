package de.dabr.prodlinesim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dabr.prodlinesim.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    public Station findByName(String name);
    
}
