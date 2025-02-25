package com.example.interntask.web;

import com.example.interntask.models.Users;
import com.example.interntask.repository.UserJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

public class UserController {
    final private UserJpaRepository userRepository;
    final  private PasswordEncoder passwordEncoder;



    public UserController(UserJpaRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/register")
            public String Register()
    {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,@RequestParam String email,String password)
    {

        Users users=new Users(name,email,password);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepository.save(users);
        return "redirect:/login";
    }


}
