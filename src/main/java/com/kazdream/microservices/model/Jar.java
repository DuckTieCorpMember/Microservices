package com.kazdream.microservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "jars")
public class Jar extends AuditModel {
    @Id
    @GeneratedValue(generator = "jar_generator")
    @SequenceGenerator(
            name = "jar_generator",
            sequenceName = "jar_sequence",
            initialValue = 123
    )
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String link;
    @ManyToOne
    @JoinColumn(name = "microservice_id")
    @JsonBackReference
    private Microservice microservice;

    public Jar() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Microservice getMicroservice() {
        return microservice;
    }

    public void setMicroservice(Microservice microservice) {
        this.microservice = microservice;
    }
}
