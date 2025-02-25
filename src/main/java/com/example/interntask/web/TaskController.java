package com.example.interntask.web;

import com.example.interntask.models.Project;
import com.example.interntask.models.Task;
import com.example.interntask.service.ProjectService;
import com.example.interntask.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller

public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("/tasks")
    public String ListAll(Model model)
    {
        List<Task> taskList=taskService.listAll();
        model.addAttribute("tasks",taskList);
        return "task/listTask";

    }

    @GetMapping("tasks/add")
    public String ShowAdd(Model model, HttpSession session)
    {
        Long projectId= (Long) session.getAttribute("projectId");
        Project project=projectService.findById(projectId);
        model.addAttribute("project",project);
        return "task/taskForm";

    }
    @PostMapping("/tasks")
    public String AddProject(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date due_date,
                             HttpSession session)
    {

        Long projectId= (Long) session.getAttribute("projectId");
        taskService.create(title,description,due_date,projectId);
        return "redirect:/tasks";

    }
    @GetMapping("/tasks/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model, HttpSession session)
    {

        model.addAttribute("task",taskService.findById(id));
        Long projectId= (Long) session.getAttribute("projectId");
        Project project=projectService.findById(projectId);
        model.addAttribute("project",project);

        return "task/Taskform";
    }

    @PostMapping("/tasks/{id}")
    public String UpdateProject(@PathVariable Long id,
                                @RequestParam String title,
                                @RequestParam String description,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date due_date,
                                HttpSession session)
    {


        Long projectId= (Long) session.getAttribute("projectId");
        taskService.update(id,title,description,due_date,projectId);
        return "redirect:/tasks";

    }
    @PostMapping("/tasks/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.taskService.delete(id);
        return "redirect:/tasks";
    }




}
