package com.example.interntask.service.impl;

import com.example.interntask.models.Project;
import com.example.interntask.models.Users;
import com.example.interntask.models.exceptions.InvalidProjectId;
import com.example.interntask.models.exceptions.InvalidUserId;
import com.example.interntask.repository.ProjectJpaRepository;
import com.example.interntask.repository.UserJpaRepository;
import com.example.interntask.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectJpaRepository projectJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public ProjectServiceImpl(ProjectJpaRepository projectJpaRepository, UserJpaRepository userJpaRepository) {
        this.projectJpaRepository = projectJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<Project> listAll(String email) {
        Users users = userJpaRepository.findByEmail(email).orElseThrow(InvalidUserId::new);
        return projectJpaRepository.findAllByUser(users);
    }

    @Override
    public Project findById(Long id) {
        return projectJpaRepository.findById(id).orElseThrow(InvalidProjectId::new);
    }

    @Override
    public Project create(String name, String description, String email) {
        Users users = userJpaRepository.findByEmail(email).orElseThrow(InvalidUserId::new);
        Project project = new Project(name, description, users);
        projectJpaRepository.save(project);
        return project;
    }

    @Override
    public Project update(Long id, String name, String description, String email) {
        Users users = userJpaRepository.findByEmail(email).orElseThrow(InvalidUserId::new);
        Project project=projectJpaRepository.findById(id).orElseThrow(InvalidProjectId::new);
        project.setName(name);
        project.setDescription(description);
        project.setUser(users);
        projectJpaRepository.save(project);
        return project;
    }

    @Override
    public void delete(Long id) {
        Project project=projectJpaRepository.findById(id).orElseThrow(InvalidProjectId::new);
         projectJpaRepository.delete(project);
    }
}
