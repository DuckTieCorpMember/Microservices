package com.kazdream.microservices.controller;

import com.kazdream.microservices.model.Microservice;
import com.kazdream.microservices.repository.MicroserviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroserviceController {
    @Autowired
    MicroserviceRepository microserviceRepository;

    @GetMapping("/microservices")
    public Page<Microservice> getMicroservices(Pageable pageable){
        return  microserviceRepository.findAll(pageable);
    }
}
