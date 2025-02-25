package com.example.interntask.repository;

import com.example.interntask.models.Project;
import com.example.interntask.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectJpaRepository extends JpaRepository<Project,Long> {
    List<Project> findAllByUser(Users user);
}
