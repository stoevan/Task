package com.example.interntask.service;

import com.example.interntask.models.Project;

import java.util.List;

public interface ProjectService {

    List<Project> listAll(String email);
    Project findById(Long id);
    Project create(String name,String description,String email);
    Project update(Long id,String name,String description,String  email);
    void delete(Long id);
}
