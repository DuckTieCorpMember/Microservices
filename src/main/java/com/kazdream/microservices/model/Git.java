package com.kazdream.microservices.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "gits")
public class Git {
    @Id
    @GeneratedValue(generator = "git_generator")
    @SequenceGenerator(
            name = "git_generator",
            sequenceName = "git_sequence",
            initialValue = 1
    )
    private Long id;

    @NotBlank
    @Size(min=3, max=100)
    private String gitName;
    @NotBlank
    @Size(min=3, max=200)
    private String gitLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGitName() {
        return gitName;
    }

    public void setGitName(String gitName) {
        this.gitName = gitName;
    }

    public String getGitLink() {
        return gitLink;
    }

    public void setGitLink(String gitLink) {
        this.gitLink = gitLink;
    }
}
