package com.kazdream.microservices.repository;

import com.kazdream.microservices.model.Jar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JarRepository extends JpaRepository<Jar, Long> {

}
