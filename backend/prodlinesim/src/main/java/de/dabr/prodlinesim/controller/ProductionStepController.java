package de.dabr.prodlinesim.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.dabr.prodlinesim.model.ProductionStep;
import de.dabr.prodlinesim.service.ProductionStepService;

@RestController
@RequestMapping("/production-step")
public class ProductionStepController {

    private final ProductionStepService productionStepService;

    public ProductionStepController(ProductionStepService productionStepService) {
        this.productionStepService = productionStepService;
    }

    @GetMapping("/findall")
    public List<ProductionStep> findAllProductionSteps() {
        return productionStepService.findAllProductionSteps();
    }

    @PutMapping("/addnew")
    public ResponseEntity<?> addNewProductionStep(@RequestParam String productionStepName) {
        return productionStepService.addNewProductionStep(productionStepName);
    }

    @PutMapping("/add-employee-to-productionstep")
    public ResponseEntity<ProductionStep> addEmployeeToProductionStep(@RequestParam Long employeeId,
            @RequestParam Long productionStepId) {
        return productionStepService.addEmployeeToProductionStep(employeeId, productionStepId);
    }

    @DeleteMapping("/delete-employee-from-productionstep")
    public ResponseEntity<ProductionStep> removeEmployeeFromProductionStep(@RequestParam Long employeeId, @RequestParam Long productionStepId) {
        return productionStepService.removeEmployeeFromProductionStep(employeeId, productionStepId);
    }
}
