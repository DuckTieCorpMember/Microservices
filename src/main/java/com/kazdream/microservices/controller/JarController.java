package com.kazdream.microservices.controller;

import com.kazdream.microservices.exception.ResourceNotFoundException;
import com.kazdream.microservices.model.Jar;
import com.kazdream.microservices.repository.JarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class JarController {
    @Autowired
    JarRepository jarRepository;

    @GetMapping("/jars")
    public Page<Jar> getProjects(Pageable pageable){
        return jarRepository.findAll(pageable);
    }

    @PostMapping("/jars")
    public Jar createProject(@Valid @RequestBody Jar jar){
        return jarRepository.save(jar);
    }

    @PutMapping("/jars/{jarId}")
    public Jar updateProject(@PathVariable Long jarId,
                                 @Valid @RequestBody Jar jarRequest){
        return jarRepository.findById(jarId)
                .map(jar -> {
                    jar.setLink(jarRequest.getLink());
                    jar.setName(jarRequest.getName());
                    return jarRepository.save(jar);
                }).orElseThrow(()->new ResourceNotFoundException("Jar is not found in database."));
    }

    @DeleteMapping("/jars/{jarId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long jarId){
        return jarRepository.findById(jarId)
                .map(jar -> {
                    jarRepository.delete(jar);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Jar is not found"));
    }
}
