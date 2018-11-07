package com.kazdream.microservices.controller;

import com.kazdream.microservices.exception.ResourceNotFoundException;
import com.kazdream.microservices.model.Project;
import com.kazdream.microservices.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/projects")
    public Page<Project> getProjects(Pageable pageable){
        return projectRepository.findAll(pageable);
    }

    @PostMapping("/projects")
    public Project createProject(@Valid @RequestBody Project project){
        return projectRepository.save(project);
    }

    @PutMapping("/projects/{projectId}")
    public Project updateProject(@PathVariable Long projectId,
                                 @Valid @RequestBody Project projectRequest){
        return projectRepository.findById(projectId)
                .map(project -> {
                    project.setLink(projectRequest.getLink());
                    project.setName(projectRequest.getName());
                    return projectRepository.save(project);
                }).orElseThrow(()->new ResourceNotFoundException("Project is not found in database."));
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId){
        return projectRepository.findById(projectId)
                .map(project -> {
                    projectRepository.delete(project);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Project is not found"));
    }
}
