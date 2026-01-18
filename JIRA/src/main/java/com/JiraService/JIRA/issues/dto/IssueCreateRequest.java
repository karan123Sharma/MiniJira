package com.JiraService.JIRA.issues.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueCreateRequest {
    private String title;
    private String description;
    private String priority;
    private String issueType;
    private Long assigneeUserId;
    private LocalDate dueDate;
}
