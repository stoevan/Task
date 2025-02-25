package com.example.interntask.service.impl;

import com.example.interntask.models.Project;
import com.example.interntask.models.Task;
import com.example.interntask.models.exceptions.InvalidProjectId;
import com.example.interntask.models.exceptions.InvalidTaskId;
import com.example.interntask.repository.ProjectJpaRepository;
import com.example.interntask.repository.TaskJpaRepository;
import com.example.interntask.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskJpaRepository taskJpaRepository;
    private final ProjectJpaRepository projectJpaRepository;

    public TaskServiceImpl(TaskJpaRepository taskJpaRepository, ProjectJpaRepository projectJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
        this.projectJpaRepository = projectJpaRepository;
    }

    @Override
    public List<Task> listAll() {
         return taskJpaRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskJpaRepository.findById(id).orElseThrow(InvalidTaskId::new);
    }

    @Override
    public Task create(String title, String description, Date due_date, Long project) {
        Project project1=projectJpaRepository.findById(project).orElseThrow(InvalidProjectId::new);
        Task task=new Task(title,description,due_date,project1);
        taskJpaRepository.save(task);
        return task;
    }

    @Override
    public Task update(Long id, String title, String description, Date due_date, Long project) {
        Project project1=projectJpaRepository.findById(project).orElseThrow(InvalidProjectId::new);
        Task task=taskJpaRepository.findById(id).orElseThrow(InvalidTaskId::new);
        task.setTitle(title);
        task.setDescription(description);
        task.setDue_date(due_date);
        task.setProject(project1);
        taskJpaRepository.save(task);
        return task;
    }

    @Override
    public void delete(Long id) {
        Task task=taskJpaRepository.findById(id).orElseThrow(InvalidTaskId::new);
        taskJpaRepository.delete(task);

    }

    @Override
    public List<Task> taskByPoject(Long projectId) {
        return taskJpaRepository.findAllByProjectId(projectId);
    }
}
