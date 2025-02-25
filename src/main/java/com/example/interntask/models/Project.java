package com.example.interntask.models;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;

    public Project() {
    }

    public Project(String name, String description, Users user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
