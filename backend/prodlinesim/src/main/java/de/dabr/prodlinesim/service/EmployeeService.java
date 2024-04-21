package de.dabr.prodlinesim.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import de.dabr.prodlinesim.model.Employee;
import de.dabr.prodlinesim.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void addDefaultUsers() {
        employeeRepository.saveAll(Arrays.asList(
                new Employee("Daniel", "Mueller"),
                new Employee("Peter", "Schulze")));
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void addNewEmployee(String firstName, String lastName) {
        employeeRepository.save(new Employee(firstName, lastName));
    }

}
