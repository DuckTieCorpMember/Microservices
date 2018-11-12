package com.kazdream.microservices.service;

import com.kazdream.microservices.model.Jar;

import java.util.List;

public interface JarService {
    public abstract List<Jar> getJars();
    public abstract void createJar(Jar jar);
    public abstract void updateJar(Long id, Jar jarRequest);
    public abstract void deleteJar(Long id);
}
