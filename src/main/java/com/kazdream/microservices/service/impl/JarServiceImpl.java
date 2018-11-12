package com.kazdream.microservices.service.impl;

import com.kazdream.microservices.exception.ResourceNotFoundException;
import com.kazdream.microservices.model.Jar;
import com.kazdream.microservices.repository.JarRepository;
import com.kazdream.microservices.service.JarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JarServiceImpl implements JarService {
    @Autowired
    JarRepository jarRepository;

    @Override
    public List<Jar> getJars() {
        return jarRepository.findAll();
    }

    @Override
    public void createJar(Jar jar) {
        jarRepository.save(jar);
    }

    @Override
    public void updateJar(Long id, Jar jarRequest) {
        jarRepository.findById(id)
                .map(jar -> {
                    jar.setLink(jarRequest.getLink());
                    jar.setName(jarRequest.getName());
                    return jarRepository.save(jar);
                }).orElseThrow(()->new ResourceNotFoundException("Jar is not found in database."));
    }

    @Override
    public void deleteJar(Long id) {
        jarRepository.findById(id)
                .map(jar -> {
                    jarRepository.delete(jar);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Jar is not found"));
    }
}
