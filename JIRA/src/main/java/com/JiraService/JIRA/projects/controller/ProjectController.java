package com.JiraService.JIRA.projects.controller;

import com.JiraService.JIRA.projects.dto.AddProjectMemberRequest;
import com.JiraService.JIRA.projects.dto.ProjectCreateRequest;
import com.JiraService.JIRA.projects.model.Project;
import com.JiraService.JIRA.projects.model.ProjectMembers;
import com.JiraService.JIRA.projects.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectController {

    @Autowired
    public ProjectService projectService;


    private Long currentUserId() {
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/createProject")
    public ResponseEntity<Project> createProject(@RequestBody ProjectCreateRequest projectCreateRequest){
        Project project = projectService.createProject(currentUserId(), projectCreateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @GetMapping("/getProjectDetails")
    public ResponseEntity<Project> getProjectDetails(@RequestParam String projectKey){
        Project project = projectService.getProjectDetails(currentUserId(), projectKey);
        return ResponseEntity.status(HttpStatus.OK).body(project);
    }

    @PostMapping("/addProjectMember")
    public ResponseEntity<ProjectMembers> addProjectMember(@RequestBody AddProjectMemberRequest addProjectMemberRequest, @RequestParam Long projectId){
        ProjectMembers projectMembers = projectService.addProjectMember(projectId, addProjectMemberRequest);
        return ResponseEntity.status(HttpStatus.OK).body(projectMembers);
    }

    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<ProjectMembers>> listMembers(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.listProjectMembers(projectId));
    }
}
