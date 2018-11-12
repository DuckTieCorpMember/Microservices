package com.kazdream.microservices.service;

import com.kazdream.microservices.model.Project;

import java.util.List;

public interface ProjectService {
    public abstract List<Project> getProjects();
    public abstract void createProject(Project project);
    public abstract void updateProject(Long id, Project projectRequest);
    public abstract void deleteProject(Long id);
}
