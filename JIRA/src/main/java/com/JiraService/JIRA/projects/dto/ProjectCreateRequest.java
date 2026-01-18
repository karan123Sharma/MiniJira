package com.JiraService.JIRA.projects.dto;

import lombok.Data;

@Data
public class ProjectCreateRequest {
    private String projectKey;
    private String name;
    private String description;
}
