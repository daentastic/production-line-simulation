package de.dabr.prodlinesim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dabr.prodlinesim.model.ProductionStep;

public interface ProductionStepRepository extends JpaRepository<ProductionStep, Long>{
    
    public Optional<ProductionStep> findByName(String name);

}
