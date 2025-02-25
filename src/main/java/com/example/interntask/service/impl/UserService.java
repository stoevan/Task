package com.example.interntask.service.impl;

import com.example.interntask.models.Users;
import com.example.interntask.repository.UserJpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService
{
    final private UserJpaRepository userRepository;
    final private PasswordEncoder passwordEncoder;


    public UserService(UserJpaRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user= userRepository.findByEmail(email);
        if(user.isPresent())
        {
            var UserObj=user.get();
            return User.builder()
                    .username(UserObj.getEmail())
                    .password(UserObj.getPassword())

                    .build();
        }
        else
        {
            throw new UsernameNotFoundException(email);
        }
    }



}


