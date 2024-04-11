package com.smart.smartauth.smartauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.responseDTOs.TaskResponse;
import com.smart.smartauth.smartauth.services.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PatchMapping("/update")
  public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
    User updatedUser = userService.updateUser(user);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<TaskResponse<Integer>> deleteUser(){
    TaskResponse<Integer> taskResponse = userService.deleteUser();
    return ResponseEntity.ok(taskResponse);
  }
  
}
