package de.dabr.prodlinesim.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import de.dabr.prodlinesim.DTOs.EmployeeDTO;
import de.dabr.prodlinesim.model.Employee;
import de.dabr.prodlinesim.model.Station;
import de.dabr.prodlinesim.repository.EmployeeRepository;
import de.dabr.prodlinesim.repository.StationRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.annotation.PostConstruct;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final StationRepository stationRepository;

    public EmployeeService(EmployeeRepository employeeRepository, StationRepository stationRepository) {
        this.employeeRepository = employeeRepository;
        this.stationRepository = stationRepository;
    }

    @PostConstruct
    public void addDefaultUsers() {

        Station generalAssembly = new Station("General Assembly");
        stationRepository.save(generalAssembly);

        employeeRepository.saveAll(Arrays.asList(
                new Employee("Daniel", "Mueller", generalAssembly),
                new Employee("Stefan", "Kopp", generalAssembly),
                new Employee("Peter", "Schulze", generalAssembly)));
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<?> addNewEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findByFirstNameAndLastName(employeeDTO.getFirstName(),
                employeeDTO.getLastName());

        if (!optionalEmployee.isPresent()) {
            Employee employee = new Employee();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setStation(employeeDTO.getStation());
            employeeRepository.save(employee);
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.badRequest().body(new ErrorMessage("Already in database"));
        }
    }

    public ResponseEntity<?> removeUser(@RequestParam Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body(new ErrorMessage("User not found"));
        }
    }
}
