package de.dabr.prodlinesim.service;

import java.util.List;
import java.util.Optional;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import de.dabr.prodlinesim.model.Employee;
import de.dabr.prodlinesim.model.ProductionStep;
import de.dabr.prodlinesim.repository.EmployeeRepository;
import de.dabr.prodlinesim.repository.ProductionStepRepository;

@Service
public class ProductionStepService {

    private final ProductionStepRepository productionStepRepository;
    private final EmployeeRepository employeeRepository;

    public ProductionStepService(
            ProductionStepRepository productionStepRepository, 
            EmployeeRepository employeeRepository) {
        this.productionStepRepository = productionStepRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<ProductionStep> findAllProductionSteps() {
        return productionStepRepository.findAll();
    }

    public ResponseEntity<?> addNewProductionStep(String productionStepName) {
        Optional<ProductionStep> optionalProductionStep = productionStepRepository.findByName(productionStepName); 
        if (!optionalProductionStep.isPresent()) {
            ProductionStep productionStep = new ProductionStep(productionStepName, null);
            ProductionStep newProductionStep = productionStepRepository.save(productionStep);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProductionStep);
        } else {
            return ResponseEntity.badRequest().body(new ErrorMessage("Name already in Database"));
        }
    }

    public ResponseEntity<ProductionStep> addEmployeeToProductionStep(Long employeeId, Long productionStepId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Optional<ProductionStep> optionalProductionStep = productionStepRepository.findById(productionStepId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (optionalProductionStep.isPresent()) {
                ProductionStep productionStep = optionalProductionStep.get();
                productionStep.addEmployeeToProductionStep(employee);
                productionStepRepository.save(productionStep);
                return ResponseEntity.ok(productionStep);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<ProductionStep> removeEmployeeFromProductionStep(Long employeeId, Long productionStepId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        Optional<ProductionStep> productionStepOptional = productionStepRepository.findById(productionStepId);

        if (employeeOptional.isPresent() && productionStepOptional.isPresent()) {
            ProductionStep productionStep = productionStepOptional.get();
            Employee employee = employeeOptional.get();
            productionStep.removeEmployeeFromProductionStep(employee);
            productionStepRepository.save(productionStep);
            return ResponseEntity.ok(productionStep);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
