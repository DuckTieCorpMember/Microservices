package com.kazdream.microservices.controller;

import com.kazdream.microservices.exception.ResourceNotFoundException;
import com.kazdream.microservices.model.Jar;
import com.kazdream.microservices.model.Microservice;
import com.kazdream.microservices.model.Project;
import com.kazdream.microservices.repository.JarRepository;
import com.kazdream.microservices.repository.MicroserviceRepository;
import com.kazdream.microservices.repository.ProjectRepository;
import com.kazdream.microservices.service.MicroserviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MicroserviceController {

    Logger logger = LoggerFactory.getLogger(MicroserviceController.class);

    @Autowired
    MicroserviceService microserviceService;

    @GetMapping("/microservices")
    public List<Microservice> getMicroservices(){
        return  microserviceService.getMicroservices();
    }

    @PostMapping("/microservices")
    public ResponseEntity<Object> createMicroservice(@Valid @RequestBody Microservice microservice){
        microserviceService.createMicroservice(microservice);
        return new ResponseEntity<>("Microservice created successfully", HttpStatus.OK);
    }

    @PutMapping("/microservices/{serviceID}")
    public ResponseEntity<Object> updateMicroservice(@PathVariable Long serviceID,
                         @Valid @RequestBody Microservice serviceRequest){
        microserviceService.updateProject(serviceID, serviceRequest);
        return new ResponseEntity<>("Microservice updated successfully", HttpStatus.OK);
    }
}
