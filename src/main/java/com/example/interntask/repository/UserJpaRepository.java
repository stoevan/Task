package com.example.interntask.repository;

import com.example.interntask.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
}
