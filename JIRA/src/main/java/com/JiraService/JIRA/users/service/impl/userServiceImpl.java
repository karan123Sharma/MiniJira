package com.JiraService.JIRA.users.service.impl;

import com.JiraService.JIRA.users.model.User;
import com.JiraService.JIRA.users.repository.UserRepository;
import com.JiraService.JIRA.users.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userServiceImpl implements userService {

    @Autowired
    private UserRepository userRepository;

    public User getUserDetails(String userId){
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }
}
