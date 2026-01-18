package com.JiraService.JIRA.users.service;

import com.JiraService.JIRA.users.model.User;
import org.springframework.stereotype.Service;

@Service
public interface userService {

    public User getUserDetails(String userId);
}
