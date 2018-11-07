package com.kazdream.microservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "microservices")
public class Microservice extends AuditModel {
    @Id
    @GeneratedValue(generator = "service_generator")
    @SequenceGenerator(
            name = "service_generator",
            sequenceName = "service_sequence",
            initialValue = 11
    )
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String git;

    @NotBlank
    private String description;

    @NotBlank
    private String endpoints;

    @OneToMany(mappedBy = "microservice")
    @JsonManagedReference
    private List<Jar> jars;

    @ManyToMany(mappedBy = "microservices")
    private List<Project> projects;

    public Microservice() {
    }

    public Microservice(@NotBlank String name, @NotBlank String git, @NotBlank String description, @NotBlank String endpoints) {
        this.name = name;
        this.git = git;
        this.description = description;
        this.endpoints = endpoints;
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

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(String endpoints) {
        this.endpoints = endpoints;
    }

    public List<Jar> getJars() {
        return jars;
    }

    public void setJars(List<Jar> jars) {
        this.jars = jars;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
