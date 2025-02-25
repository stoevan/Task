package com.example.interntask.service;

import com.example.interntask.models.Task;

import java.util.Date;
import java.util.List;

public interface TaskService {
    List<Task> listAll();
    Task findById(Long id);
    Task create(String title, String description, Date due_date, Long project);
    Task update(Long id,String title, String description, Date due_date,Long project);
    void delete(Long id);

    List<Task> taskByPoject(Long projectId);
}
