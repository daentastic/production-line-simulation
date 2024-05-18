package de.dabr.prodlinesim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.dabr.prodlinesim.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    
}
