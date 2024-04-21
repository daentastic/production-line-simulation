package de.dabr.prodlinesim.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dabr.prodlinesim.model.Employee;
import de.dabr.prodlinesim.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/show-all")
    public List<Employee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }
    //PutMapping instead of PostMapping -> Put is idempotent -> Multiple triggers produce the same result
    @PutMapping("/new-employee")
    public void addNewEmployee(String firstName, String lastName) {
        employeeService.addNewEmployee(firstName, lastName);
    }
}
