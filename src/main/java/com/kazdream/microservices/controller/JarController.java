package com.kazdream.microservices.controller;

import com.kazdream.microservices.exception.ResourceNotFoundException;
import com.kazdream.microservices.model.Jar;
import com.kazdream.microservices.repository.JarRepository;
import com.kazdream.microservices.service.JarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class JarController {
    @Autowired
    JarService jarService;

    @GetMapping("/jars")
    public ResponseEntity<Object> getJars(){
        return new ResponseEntity<>(jarService.getJars(), HttpStatus.OK);
    }

//    @PostMapping("/jars")
//    public ResponseEntity<Object> createJar(@Valid @RequestBody Jar jar){
//        jarService.createJar(jar);
//        return new ResponseEntity<>("Jar created successfully.", HttpStatus.OK);
//    }
//
//    @PutMapping("/jars/{jarId}")
//    public ResponseEntity<Object> updateJar(@PathVariable Long jarId,
//                                 @Valid @RequestBody Jar jarRequest){
//        jarService.updateJar(jarId, jarRequest);
//        return new ResponseEntity<>("Jar updated successfully", HttpStatus.OK);
//    }
//
    @DeleteMapping("/jars/{jarId}")
    public ResponseEntity<Object> deleteJar(@PathVariable Long jarId){
        jarService.deleteJar(jarId);
        return new ResponseEntity<>("Jar deleted successfully.", HttpStatus.OK);
    }
}
