package com.example.interntask.web;

import com.example.interntask.models.Project;
import com.example.interntask.models.Task;
import com.example.interntask.service.ProjectService;
import com.example.interntask.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;
    private final TaskService taskService;
    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping
    public String listAll(Principal principal, Model model)
    {
        String emial=principal.getName();
        List<Project> projects=projectService.listAll(emial);
        model.addAttribute("projects",projects);
        return "project/projectList";
    }

    @GetMapping("/add")
    public String ShowAdd()
    {
        return "project/projectForm";
    }

    @PostMapping()
    public String AddProject(@RequestParam String name,
                             @RequestParam String description,
                             Principal principal,
                             Model model)
    {
        String email=principal.getName();
        model.addAttribute("email",email);
        projectService.create(name,description,email);
        return "redirect:/projects";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable Long id, Model model, Principal principal)
    {
        String userName = principal.getName();
        model.addAttribute("email",userName);
        model.addAttribute("project",projectService.findById(id));

        return "project/projectForm";
    }
    @PostMapping("{id}")
    public String UpdateProject(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam String description,
                                Principal principal)
    {
        if(principal!=null)
        {
            String userName = principal.getName();
            projectService.update(id,name,description,userName);
        }

        return "redirect:/projects";

    }
    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        this.projectService.delete(id);
        return "redirect:/projects";
    }


    @GetMapping("/{id}/tasks")
    public String showTasks(Model model, @PathVariable Long id, HttpSession session)
    {
        List<Task> tasks=taskService.taskByPoject(id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("projectId", id);
        session.setAttribute("projectId",id);
        return "project/tasks";
    }


}
