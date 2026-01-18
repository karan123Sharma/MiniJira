package com.JiraService.JIRA.projects.dto;

import lombok.Data;

@Data
public class AddProjectMemberRequest {
    private Long userId;
    private String role;
}
