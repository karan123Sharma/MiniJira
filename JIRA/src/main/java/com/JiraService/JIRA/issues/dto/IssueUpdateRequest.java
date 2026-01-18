package com.JiraService.JIRA.issues.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueUpdateRequest {
    private String title;
    private String description;
    private String status;
    private String priority;
    private String issueType;
    private Long assigneeUserId;
    private LocalDate dueDate;
}
