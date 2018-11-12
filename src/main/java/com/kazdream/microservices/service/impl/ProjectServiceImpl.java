package com.kazdream.microservices.service.impl;

import com.kazdream.microservices.exception.ResourceNotFoundException;
import com.kazdream.microservices.model.Project;
import com.kazdream.microservices.repository.ProjectRepository;
import com.kazdream.microservices.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void createProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void updateProject(Long id, Project projectRequest) {
        projectRepository.findById(id)
                .map(project -> {
                    project.setLink(projectRequest.getLink());
                    project.setName(projectRequest.getName());
                    return projectRepository.save(project);
                }).orElseThrow(()->new ResourceNotFoundException("Project is not found in database."));
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.findById(id)
                .map(project -> {
                    projectRepository.delete(project);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Project is not found"));
    }
}
