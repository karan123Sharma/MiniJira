package com.JiraService.JIRA.users.controller;

import com.JiraService.JIRA.users.model.User;
import com.JiraService.JIRA.users.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class userController {

    @Autowired
    private userService userService;

    @GetMapping("/getUser")
    public ResponseEntity<User> getUserDetails(@RequestParam String userId){
        User userDetails = userService.getUserDetails(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
    }
}
