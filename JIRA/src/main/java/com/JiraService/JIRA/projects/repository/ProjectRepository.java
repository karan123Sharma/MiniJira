package com.JiraService.JIRA.projects.repository;

import com.JiraService.JIRA.projects.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    Project findByProjectKey(String projectKey);

    @Query("update Project p set p.issueSeq = p.issueSeq + 1 where p.id = :projectId")
    int incrementIssueSeq(@Param("projectId") Long projectId);

    @Query("select p.issueSeq from Project p where p.id = :projectId")
    Integer getIssueSeq(@Param("projectId") Long projectId);



}
