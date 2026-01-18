package com.JiraService.JIRA.issues.controller;

import com.JiraService.JIRA.issues.dto.IssueCreateRequest;
import com.JiraService.JIRA.issues.dto.IssueResponse;
import com.JiraService.JIRA.issues.dto.IssueUpdateRequest;
import com.JiraService.JIRA.issues.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class IssueController {

    @Autowired
    public IssueService issueService;

    private Long currentUserId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/projects/{projectId}/issues")
    public ResponseEntity<IssueResponse> create(@PathVariable Long projectId, @RequestBody IssueCreateRequest req) {
        return ResponseEntity.ok(issueService.createIssue(currentUserId(), projectId, req));
    }

    @GetMapping("/projects/{projectId}/issues")
    public ResponseEntity<List<IssueResponse>> list(@PathVariable Long projectId,
                                                    @RequestParam(required = false) String status,
                                                    @RequestParam(required = false) Long assigneeUserId) {
        return ResponseEntity.ok(issueService.listIssues(currentUserId(), projectId, status, assigneeUserId));
    }

    @GetMapping("/issues/{issueId}")
    public ResponseEntity<IssueResponse> get(@PathVariable Long issueId) {
        return ResponseEntity.ok(issueService.getIssue(currentUserId(), issueId));
    }

    @PatchMapping("/issues/{issueId}")
    public ResponseEntity<IssueResponse> update(@PathVariable Long issueId, @RequestBody IssueUpdateRequest req) {
        return ResponseEntity.ok(issueService.updateIssue(currentUserId(), issueId, req));
    }

}
