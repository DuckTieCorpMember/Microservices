package com.kazdream.microservices.service.impl;

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
import org.springframework.http.ResponseEntity;
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

    Logger logger = LoggerFactory.getLogger(MicroserviceServiceImpl.class);

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
        List<Project> allProjects = projectRepository.findAll();
        for (Project project: mm.getProjects()){
            boolean found = false;
            for (Project exists: allProjects) {
                if(exists.getName().equals(project.getName())){
                    exists.getMicroservices().add(mm);
                    found = true;
                    break;
                }
            }
            if(!found) {
                Project newProject = new Project();
                newProject.setName(project.getName());
                newProject.setLink(project.getLink());
                List<Microservice> prServices = new ArrayList<>();
                prServices.add(mm);
                newProject.setMicroservices(prServices);
                projectRepository.save(newProject);
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

                    List<Jar> ex_jar = microservice.getJars();
                    for(Jar jj: ex_jar){
                        jarRepository.delete(jj);
                    }

                    microservice.setJars(new ArrayList<Jar>());
                    for (Jar jar: serviceRequest.getJars()){
                        Jar temp = new Jar();
                        temp.setName(jar.getName());
                        temp.setLink(jar.getLink());
                        temp.setMicroservice(microservice);
                        Jar jj = jarRepository.save(temp);
                        microservice.getJars().add(jj);
                    }

                    List<Project> currentProject = microservice.getProjects();
                    for(Project current: currentProject){
                        current.getMicroservices().remove(microservice);
                        if(current.getMicroservices().size() == 0){
                            projectRepository.delete(current);
                        }
                    }
                    microservice.setProjects(new ArrayList<>());
                    List<Project> allProjects = projectRepository.findAll();
                    for (Project project: serviceRequest.getProjects()){
                        boolean found = false;
                        for (Project exists: allProjects) {
                            if(exists.getName().equals(project.getName())){
                                exists.getMicroservices().add(microservice);
                                found = true;
                                break;
                            }
                        }
                        if(!found) {
                            Project newProject = new Project();
                            newProject.setName(project.getName());
                            newProject.setLink(project.getLink());
                            List<Microservice> prServices = new ArrayList<>();
                            prServices.add(microservice);
                            newProject.setMicroservices(prServices);
                            projectRepository.save(newProject);
                        }
                        microservice.getProjects().add(project);
                    }

                    return microservice;
                }).orElseThrow(()->new ResourceNotFoundException("Microservice is not found in database."));
    }

    @Override
    public void deleteProject(Long id) {
        microserviceRepository.findById(id)
                .map(microservice -> {
                    List<Jar> jars = microservice.getJars();
                    for(Jar jar: jars){
                        jarRepository.delete(jar);
                    }
                    List<Project> currentProject = microservice.getProjects();
                    for(Project current: currentProject){
                        current.getMicroservices().remove(microservice);
                        if(current.getMicroservices().size() == 0){
                            projectRepository.delete(current);
                        }
                    }
                    microserviceRepository.delete(microservice);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Microservice is not found in database."));
    }
}
