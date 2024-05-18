package de.dabr.prodlinesim.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dabr.prodlinesim.model.Employee;
import de.dabr.prodlinesim.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @CrossOrigin()
    @GetMapping("/show-all")
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }
    
    @PostMapping("/new-employee")
    public ResponseEntity<?> addNewEmployee(String firstName, String lastName) {
        return employeeService.addNewEmployee(firstName, lastName);
    }

    @PutMapping("/start-working") 
    public void startEmployeeWork() {
    
    }
}
