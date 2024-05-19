package de.dabr.prodlinesim.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import de.dabr.prodlinesim.DTOs.EmployeeDTO;
import de.dabr.prodlinesim.model.Employee;
import de.dabr.prodlinesim.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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

    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByFirstNameAndLastName(employeeDTO.getFirstName(), employeeDTO.getLastName());
        
        if (!optionalEmployee.isPresent()) {
            Employee employee = new Employee();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employeeRepository.save(employee);
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.badRequest().body(new ErrorMessage("Already in database"));
        }
    }
}
