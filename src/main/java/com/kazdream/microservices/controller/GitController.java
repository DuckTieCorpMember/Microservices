package com.kazdream.microservices.controller;

import com.kazdream.microservices.model.Git;
import com.kazdream.microservices.repository.GitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GitController {
    @Autowired
    GitRepository gitRepository;

    @GetMapping("/gits")
    public Page<Git> getGits(Pageable pageable){
        return gitRepository.findAll(pageable);
    }

    @PostMapping("/gits")
    public Git createGit(@Valid @RequestBody Git git){
        return gitRepository.save(git);
    }

    @PutMapping("/gits/{gitId}")
    public Git updateGit(@PathVariable Long gitId, @Valid @RequestBody Git git){
        return null;
    }
}
