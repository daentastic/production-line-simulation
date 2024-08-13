package de.dabr.prodlinesim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dabr.prodlinesim.model.Sample;

public interface SampleRepository extends JpaRepository <Sample, Integer> {
    
}
