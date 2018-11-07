package com.kazdream.microservices.controller;

import com.kazdream.microservices.model.Jar;
import com.kazdream.microservices.model.Microservice;
import com.kazdream.microservices.model.Project;
import com.kazdream.microservices.repository.JarRepository;
import com.kazdream.microservices.repository.MicroserviceRepository;
import com.kazdream.microservices.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MicroserviceController {

    Logger logger = LoggerFactory.getLogger(MicroserviceController.class);

    @Autowired
    MicroserviceRepository microserviceRepository;
    @Autowired
    JarRepository jarRepository;
    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/microservices")
    public Page<Microservice> getMicroservices(Pageable pageable){
        return  microserviceRepository.findAll(pageable);
    }

    @PostMapping("/microservices")
    public Microservice createMicroservice(@Valid @RequestBody Microservice microservice){
        logger.warn("Aaaaaaaaaaaaaaaa");
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
            List<Microservice> mms = temp.getMicroservices();
            mms.add(mm);
            temp.setMicroservices(mms);
            List<Project> existing = projectRepository.findAll();
            boolean pr_exists=false;
            for (Project pr:existing) {
                if(pr.getName() == temp.getName()){
                    pr_exists=true;
                    break;
                }
            }
            if(!pr_exists)
            {
                projectRepository.save(temp);
            }
        }
        return mm;
    }
}
