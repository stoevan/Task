package com.example.interntask.config;

import com.example.interntask.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserDetailsService userDetailsService;


    public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) ->

                        requests.requestMatchers("/projects", "/register", "/tasks")
                                .permitAll()
                                .anyRequest()
                                .authenticated()

                )
                .formLogin(
                        (form) -> form
                                .permitAll()
                                .failureUrl("/login?error=BadCredentials")
                                .defaultSuccessUrl("/projects", true)
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login")
                );


        return http.build();
    }

    public UserDetailsService userDetailsService() {
        return userService;
    }
}
