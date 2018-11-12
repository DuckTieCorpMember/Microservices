package com.kazdream.microservices.service;

import com.kazdream.microservices.model.Microservice;

import java.util.List;

public interface MicroserviceService {
    public abstract List<Microservice> getMicroservices();
    public abstract void createMicroservice(Microservice microservice);
    public abstract void updateProject(Long id, Microservice serviceRequest);
    public abstract void deleteProject(Long id);
}
