package com.JiraService.JIRA.auth.service;


import com.JiraService.JIRA.auth.dto.AuthResponse;
import com.JiraService.JIRA.auth.dto.LoginRequest;
import com.JiraService.JIRA.auth.dto.SignupRequest;
import com.JiraService.JIRA.config.securityService;
import com.JiraService.JIRA.users.model.User;
import com.JiraService.JIRA.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class AuthService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public securityService securityService;

    public AuthResponse signUp(SignupRequest signupRequest) {
        AuthResponse authResponse = new AuthResponse();
        String email = signupRequest.getEmail();

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setName(signupRequest.getName());
        user.setCreatedAt(Timestamp.from(Instant.now()).toLocalDateTime());
        user.setUpdatedAt(Timestamp.from(Instant.now()).toLocalDateTime());
        User savedUser = userRepository.save(user);

        String securityToken = securityService.generateToken(user.getId());
        authResponse.setToken(securityToken);
        authResponse.setUserId(savedUser.getId());
        return authResponse;

    }

    public AuthResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        User user = userRepository.findByEmail(email);

        if (!user.isActive()) {
            throw new RuntimeException("User inactive");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = securityService.generateToken(user.getId());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserId(user.getId());
        authResponse.setToken(token);
        return authResponse;
    }
}
