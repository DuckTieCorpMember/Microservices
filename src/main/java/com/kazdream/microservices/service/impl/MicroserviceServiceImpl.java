package com.kazdream.microservices.service.impl;

import com.kazdream.microservices.exception.ResourceNotFoundException;
import com.kazdream.microservices.model.Jar;
import com.kazdream.microservices.model.Microservice;
import com.kazdream.microservices.model.Project;
import com.kazdream.microservices.repository.JarRepository;
import com.kazdream.microservices.repository.MicroserviceRepository;
import com.kazdream.microservices.repository.ProjectRepository;
import com.kazdream.microservices.service.MicroserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MicroserviceServiceImpl implements MicroserviceService {
    @Autowired
    MicroserviceRepository microserviceRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    JarRepository jarRepository;

    @Override
    public List<Microservice> getMicroservices() {
        return microserviceRepository.findAll();
    }

    @Override
    public void createMicroservice(Microservice microservice) {
        Microservice mm = microserviceRepository.save(microservice);
        for (Jar jar:mm.getJars()) {
            Jar temp = new Jar();
            temp.setLink(jar.getLink());
            temp.setName(jar.getName());
            temp.setMicroservice(mm);
            jarRepository.save(temp);
        }
        for (Project project: mm.getProjects()){
            Project temp = new Project();
            temp.setName(project.getName());
            temp.setLink(project.getLink());
            List<Microservice> mms = new ArrayList<>();
            if(temp.getMicroservices() != null) {
                for (Microservice m : temp.getMicroservices()) {
                    mms.add(m);
                }
            }
            mms.add(mm);
            temp.setMicroservices(mms);
            List<Project> existing = projectRepository.findAll();
            boolean pr_exists=false;
            for (Project pr:existing) {
                if(pr.getLink() == temp.getLink()){
                    pr_exists=true;
                    break;
                }
            }
            if(!pr_exists)
            {
                projectRepository.save(temp);
            }
        }
    }

    @Override
    public void updateProject(Long id, Microservice serviceRequest) {
        microserviceRepository.findById(id)
                .map(microservice -> {
                    microservice.setDescription(serviceRequest.getDescription());
                    microservice.setName(serviceRequest.getName());
                    microservice.setEndpoints(serviceRequest.getEndpoints());
                    microservice.setGit(serviceRequest.getGit());
                    microservice.setJars(new ArrayList<Jar>());
                    microservice.setProjects(new ArrayList<Project>());
                    Microservice saved_service = microserviceRepository.save(microservice);

                    for (Jar jar: serviceRequest.getJars()){
                        Jar temp = new Jar();
                        temp.setName(jar.getName());
                        temp.setLink(jar.getLink());
                        temp.setMicroservice(microservice);
                        jarRepository.save(temp);
                    }
                    for (Project project: serviceRequest.getProjects()){
                        Project temp = new Project();
                        temp.setName(project.getName());
                        temp.setLink(project.getLink());
                    }
                    return saved_service;
                }).orElseThrow(()->new ResourceNotFoundException("Microservice is not found in database."));
    }

    @Override
    public void deleteProject(Long id) {

    }
}
