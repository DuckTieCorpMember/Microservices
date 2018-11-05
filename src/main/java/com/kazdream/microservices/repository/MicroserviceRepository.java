package com.kazdream.microservices.repository;

import com.kazdream.microservices.model.Git;
import com.kazdream.microservices.model.Microservice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicroserviceRepository extends JpaRepository<Microservice, Long> {
    Git findByGitId(Long git_id);
}

