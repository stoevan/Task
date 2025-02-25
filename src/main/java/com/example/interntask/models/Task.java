package com.example.interntask.models;

import jakarta.persistence.*;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
@Entity

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date due_date;
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    public Task() {
    }

    public Task(String title, String description, Date due_date, Project project) {
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.project = project;
    }
}
