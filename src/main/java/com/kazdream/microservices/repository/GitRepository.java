package com.kazdream.microservices.repository;

import com.kazdream.microservices.model.Git;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitRepository extends JpaRepository<Git, Long> {

}