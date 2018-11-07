package com.kazdream.microservices.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project extends AuditModel {
    @Id
    @GeneratedValue(generator = "project_generator")
    @SequenceGenerator(
            name = "project_generator",
            sequenceName = "project_sequence",
            initialValue = 100
    )
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String link;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "microservice_project",
        joinColumns = @JoinColumn(name = "microservice_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Microservice> microservices;

    public Project() {
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

    public List<Microservice> getMicroservices() {
        return microservices;
    }

    public void setMicroservices(List<Microservice> microservices) {
        this.microservices = microservices;
    }
}
