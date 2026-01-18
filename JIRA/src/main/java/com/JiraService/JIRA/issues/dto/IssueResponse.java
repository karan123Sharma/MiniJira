package com.JiraService.JIRA.issues.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueResponse {
    private Long id;
    private Long projectId;
    private String issueKey;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String issueType;
    private Long reporterUserId;
    private Long assigneeUserId;
    private LocalDate dueDate;
}
