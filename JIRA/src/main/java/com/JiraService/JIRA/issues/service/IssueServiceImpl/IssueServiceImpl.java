package com.JiraService.JIRA.issues.service.IssueServiceImpl;

import com.JiraService.JIRA.issues.dto.IssueCreateRequest;
import com.JiraService.JIRA.issues.dto.IssueResponse;
import com.JiraService.JIRA.issues.dto.IssueUpdateRequest;
import com.JiraService.JIRA.issues.model.Issue;
import com.JiraService.JIRA.issues.repository.IssuesRepository;
import com.JiraService.JIRA.issues.service.IssueService;
import com.JiraService.JIRA.projects.model.Project;
import com.JiraService.JIRA.projects.repository.ProjectMemberRepository;
import com.JiraService.JIRA.projects.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IssueServiceImpl implements IssueService {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IssuesRepository issueRepository;

    public IssueResponse createIssue(Long currentUserId, Long projectId, IssueCreateRequest req) {
        // member check
        if (!projectMemberRepository.existsByProjectIdAndUserId(projectId, currentUserId)) {
            throw new RuntimeException("Forbidden: not a project member");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        projectRepository.incrementIssueSeq(projectId);
        Integer seq = projectRepository.getIssueSeq(projectId);

        String issueKey = project.getProjectKey() + "-" + seq;

        Issue issue = new Issue();
        issue.setProjectId(projectId);
        issue.setIssueKey(issueKey);
        issue.setTitle(req.getTitle());
        issue.setDescription(req.getDescription());
        issue.setPriority(req.getPriority() == null ? "MEDIUM" : req.getPriority().toUpperCase());
        issue.setIssueType(req.getIssueType() == null ? "TASK" : req.getIssueType().toUpperCase());
        issue.setStatus("TODO");
        issue.setReporterUserId(currentUserId);
        issue.setAssigneeUserId(req.getAssigneeUserId());
        issue.setDueDate(req.getDueDate());

        Issue saved = issueRepository.save(issue);

        return toResponse(saved);
    }

    public List<IssueResponse> listIssues(Long currentUserId, Long projectId, String status, Long assigneeUserId) {
        if (!projectMemberRepository.existsByProjectIdAndUserId(projectId, currentUserId)) {
            throw new RuntimeException("Forbidden: not a project member");
        }

        List<Issue> issues;
        if (status != null && !status.isBlank()) {
            issues = issueRepository.findByProjectIdAndStatus(projectId, status.toUpperCase());
        } else if (assigneeUserId != null) {
            issues = issueRepository.findByProjectIdAndAssigneeUserId(projectId, assigneeUserId);
        } else {
            issues = issueRepository.findByProjectId(projectId);
        }

        return issues.stream().map(this::toResponse).toList();
    }

    public IssueResponse getIssue(Long currentUserId, Long issueId) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        if (!projectMemberRepository.existsByProjectIdAndUserId(issue.getProjectId(), currentUserId)) {
            throw new RuntimeException("Forbidden: not a project member");
        }

        return toResponse(issue);
    }

    @Transactional
    public IssueResponse updateIssue(Long currentUserId, Long issueId, IssueUpdateRequest req) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        if (!projectMemberRepository.existsByProjectIdAndUserId(issue.getProjectId(), currentUserId)) {
            throw new RuntimeException("Forbidden: not a project member");
        }

        if (req.getTitle() != null) issue.setTitle(req.getTitle());
        if (req.getDescription() != null) issue.setDescription(req.getDescription());
        if (req.getStatus() != null) issue.setStatus(req.getStatus().toUpperCase());
        if (req.getPriority() != null) issue.setPriority(req.getPriority().toUpperCase());
        if (req.getIssueType() != null) issue.setIssueType(req.getIssueType().toUpperCase());
        if (req.getAssigneeUserId() != null) issue.setAssigneeUserId(req.getAssigneeUserId());
        if (req.getDueDate() != null) issue.setDueDate(req.getDueDate());

        Issue saved = issueRepository.save(issue);
        return toResponse(saved);
    }


    private IssueResponse toResponse(Issue i) {
        IssueResponse res = new IssueResponse();
        res.setId(i.getId());
        res.setProjectId(i.getProjectId());
        res.setIssueKey(i.getIssueKey());
        res.setTitle(i.getTitle());
        res.setDescription(i.getDescription());
        res.setStatus(i.getStatus());
        res.setPriority(i.getPriority());
        res.setIssueType(i.getIssueType());
        res.setReporterUserId(i.getReporterUserId());
        res.setAssigneeUserId(i.getAssigneeUserId());
        res.setDueDate(i.getDueDate());
        return res;
    }
}
