package com.JiraService.JIRA.projects.repository;

import com.JiraService.JIRA.projects.model.ProjectMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMembers,Long> {
    ProjectMembers findByProjectIdAndUserId(Long projectId, Long UserId);

    List<ProjectMembers> findByProjectId(Long projectId);

    Boolean existsByProjectIdAndUserId(Long projectId, Long userId);
}
