package com.example.interntask.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity

@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Users() {
    }
}
