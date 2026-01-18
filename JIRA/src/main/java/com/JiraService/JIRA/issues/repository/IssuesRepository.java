package com.JiraService.JIRA.issues.repository;

import com.JiraService.JIRA.issues.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IssuesRepository extends JpaRepository<Issue,Long> {

    List<Issue> findByProjectId(Long projectId);
    List<Issue> findByProjectIdAndStatus(Long projectId, String status);
    List<Issue> findByProjectIdAndAssigneeUserId(Long projectId, Long assigneeUserId);

}
