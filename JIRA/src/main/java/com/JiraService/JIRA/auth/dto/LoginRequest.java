package com.JiraService.JIRA.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    public String email;
    public String password;
}
