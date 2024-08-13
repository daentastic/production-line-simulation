package de.dabr.prodlinesim.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.dabr.prodlinesim.model.Sample;
import de.dabr.prodlinesim.repository.SampleRepository;
import jakarta.annotation.PostConstruct;

@RestController(value = "/sample")
public class SampleController {

    @Autowired
    private SampleRepository sampleRepository;

    @PostConstruct
    public void preConstructSamples() {
        Sample sample1 = new Sample("FirstSample", 10);
        Sample sapmle2 = new Sample("SecondSample", 20);

        sampleRepository.save(sample1);
        sampleRepository.save(sapmle2);
    }

    @GetMapping("/by-pathvariable/{id}")
    public ResponseEntity<Sample> getSampleByPathVariable(@PathVariable int id) {
        Optional<Sample> optionalSample = sampleRepository.findById(id);

        if (optionalSample.isPresent()) {
            return ResponseEntity.ok(optionalSample.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/by-requestparam")
    public ResponseEntity<Sample> getSampleByRequestParam(@RequestParam int id) {
        Optional<Sample> optionalSample = sampleRepository.findById(id);

        if (optionalSample.isPresent()) {
            return ResponseEntity.ok(optionalSample.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
