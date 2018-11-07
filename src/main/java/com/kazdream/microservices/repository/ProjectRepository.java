package com.kazdream.microservices.repository;

import com.kazdream.microservices.model.Microservice;
import com.kazdream.microservices.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}