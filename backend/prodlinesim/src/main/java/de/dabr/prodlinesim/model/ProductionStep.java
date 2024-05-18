package de.dabr.prodlinesim.model;

import java.util.ArrayList;
import java.util.List;

import de.dabr.prodlinesim.exceptions.EmployeeNotFoundException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter


@Entity
public class ProductionStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany
    private List<Employee> employees = new ArrayList<>();

    public ProductionStep() {}

    public ProductionStep(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public void addEmployeeToProductionStep(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployeeFromProductionStep(Employee employee) {
        if(employees.contains(employee)) {
            try {
                employees.remove(employee);
            } catch(EmployeeNotFoundException enfe) {
                enfe.printStackTrace();
            }
        } 
    }    
}
