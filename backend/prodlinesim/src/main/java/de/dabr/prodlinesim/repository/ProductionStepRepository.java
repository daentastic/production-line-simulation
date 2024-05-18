package de.dabr.prodlinesim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dabr.prodlinesim.model.ProductionStep;

public interface ProductionStepRepository extends JpaRepository<ProductionStep, Long>{
    
}
