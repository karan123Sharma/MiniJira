package com.JiraService.JIRA.projects.service.projectServiceimpl;

import com.JiraService.JIRA.projects.dto.AddProjectMemberRequest;
import com.JiraService.JIRA.projects.dto.ProjectCreateRequest;
import com.JiraService.JIRA.projects.model.Project;
import com.JiraService.JIRA.projects.model.ProjectMembers;
import com.JiraService.JIRA.projects.repository.ProjectMemberRepository;
import com.JiraService.JIRA.projects.repository.ProjectRepository;
import com.JiraService.JIRA.projects.service.ProjectService;
import com.JiraService.JIRA.users.model.User;
import com.JiraService.JIRA.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    public  ProjectRepository projectRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ProjectMemberRepository projectMemberRepository;

    public Project createProject(Long currentUserId, ProjectCreateRequest createProject){
        Project existingProject = projectRepository.findByProjectKey(createProject.getProjectKey());
        if(existingProject != null){
            throw  new RuntimeException("Project Already Exist");
        }
        Project project = new Project();
        project.setActive(true);
        project.setName(createProject.getName());
        project.setDescription(createProject.getDescription());
        project.setProjectKey(createProject.getProjectKey());
        project.setIssueSeq(0);
        project.setOwnerUserId(currentUserId);
        project.setCreatedAt(Timestamp.from(Instant.now()).toLocalDateTime());
        project.setUpdatedAt(Timestamp.from(Instant.now()).toLocalDateTime());

        projectRepository.save(project);
        return project;
    }


    public Project getProjectDetails(Long userId, String projectKey){
        return projectRepository.findByProjectKey(projectKey);
    }

    public ProjectMembers addProjectMember(Long projectId, AddProjectMemberRequest addProjectMemberRequest) {
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isEmpty()){
            throw new RuntimeException("Project is Not present");
        }
        Optional<User> user = userRepository.findById(addProjectMemberRequest.getUserId().toString());
        if(user.isEmpty()){
            throw new RuntimeException("User doesn't Exist");
        }
        ProjectMembers existingProjectMember = projectMemberRepository.findByProjectIdAndUserId(projectId, addProjectMemberRequest.getUserId());
        if(existingProjectMember != null){
            throw  new RuntimeException("User Already present in the Project");
        }
        ProjectMembers projectMembers = new ProjectMembers();
        projectMembers.setRole(addProjectMemberRequest.getRole());
        projectMembers.setCreatedAt(Timestamp.from(Instant.now()).toLocalDateTime());
        projectMembers.setUserId(addProjectMemberRequest.getUserId());
        projectMembers.setProjectId(projectId);
        return projectMemberRepository.save(projectMembers);
    }

    public List<ProjectMembers> listProjectMembers(Long projectId){
        return projectMemberRepository.findByProjectId(projectId);
    }
}
