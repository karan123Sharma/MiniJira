package com.JiraService.JIRA.auth.dto;

import lombok.Data;

@Data
public class AuthResponse {
    public String token;
    public Long userId;

}
