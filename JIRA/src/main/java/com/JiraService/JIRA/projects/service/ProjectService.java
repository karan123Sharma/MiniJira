package com.JiraService.JIRA.projects.service;

import com.JiraService.JIRA.projects.dto.AddProjectMemberRequest;
import com.JiraService.JIRA.projects.dto.ProjectCreateRequest;
import com.JiraService.JIRA.projects.model.Project;
import com.JiraService.JIRA.projects.model.ProjectMembers;

import java.util.List;

public interface ProjectService {

    Project createProject(Long userId, ProjectCreateRequest projectCreateRequest);

    Project getProjectDetails(Long userId, String projectKey);

    ProjectMembers addProjectMember(Long projectID, AddProjectMemberRequest addProjectMemberRequest);

    List<ProjectMembers> listProjectMembers(Long projectId);
}
