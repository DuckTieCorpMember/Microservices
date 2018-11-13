package com.kazdream.microservices.controller;

import com.kazdream.microservices.exception.ResourceNotFoundException;
import com.kazdream.microservices.model.Project;
import com.kazdream.microservices.service.ProjectService;
import org.hibernate.loader.collection.OneToManyJoinWalker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public List<Project> getProjects(){
        return projectService.getProjects();
    }

//    @PostMapping("/projects")
//    public ResponseEntity<Object> createProject(@Valid @RequestBody Project project){
//        projectService.createProject(project);
//        return new ResponseEntity<>("Project created successfully.", HttpStatus.OK);
//    }
//
//    @PutMapping("/projects/{projectId}")
//    public ResponseEntity<Object> updateProject(@PathVariable Long projectId,
//                                 @Valid @RequestBody Project projectRequest){
//        projectService.updateProject(projectId, projectRequest);
//        return new ResponseEntity<>("Project updated successfully.", HttpStatus.OK);
//    }
//
    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long projectId){
        projectService.deleteProject(projectId);
        return new ResponseEntity<>("Project deleted successfully.", HttpStatus.OK);
    }
}
