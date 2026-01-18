package com.JiraService.JIRA.issues.service;

import com.JiraService.JIRA.issues.dto.IssueCreateRequest;
import com.JiraService.JIRA.issues.dto.IssueResponse;
import com.JiraService.JIRA.issues.dto.IssueUpdateRequest;

import java.util.List;

public interface IssueService {

    public IssueResponse createIssue(Long currentUserId, Long projectId, IssueCreateRequest req);

    public List<IssueResponse> listIssues(Long currentUserId, Long projectId, String status, Long assigneeUserId);

    public IssueResponse getIssue(Long currentUserId, Long issueId);

    public IssueResponse updateIssue(Long currentUserId, Long issueId, IssueUpdateRequest req);
}
