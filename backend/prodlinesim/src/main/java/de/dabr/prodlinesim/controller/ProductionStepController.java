package de.dabr.prodlinesim.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.dabr.prodlinesim.model.Employee;
import de.dabr.prodlinesim.model.ProductionStep;
import de.dabr.prodlinesim.repository.EmployeeRepository;
import de.dabr.prodlinesim.repository.ProductionStepRepository;

@RestController("/production-step")
public class ProductionStepController {

    private final ProductionStepRepository productionStepRepository;
    private final EmployeeRepository employeeRepository;

    public ProductionStepController(ProductionStepRepository productionStepRepository,
            EmployeeRepository employeeRepository) {
        this.productionStepRepository = productionStepRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/findall")
    public List<ProductionStep> findAllProductionSteps() {
        return productionStepRepository.findAll();
    }

    @PutMapping("/addnew")
    public ResponseEntity<ProductionStep> addNewProductionStep(@RequestParam String productionStepName) {
        ProductionStep productionStep = new ProductionStep(productionStepName, null);
        ProductionStep newProductionStep = productionStepRepository.save(productionStep);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProductionStep);
    }

    @PutMapping("/add-employee-to-productionstep")
    public ResponseEntity<ProductionStep> addEmployeeToProductionStep(@RequestParam Long employeeId, @RequestParam Long productionStepId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Optional<ProductionStep> optionalProductionStep = productionStepRepository.findById(productionStepId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            if (optionalProductionStep.isPresent()) {
                ProductionStep productionStep = optionalProductionStep.get();
                productionStep.addEmployeeToProductionStep(employee);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
