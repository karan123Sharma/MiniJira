package com.JiraService.JIRA.projects.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "project_members")
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role", length = 20, nullable = false)
    private String role; // OWNER / ADMIN / MEMBER

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
