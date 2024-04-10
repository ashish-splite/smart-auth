package com.smart.smartauth.smartauth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.smartauth.smartauth.responseDTOs.TaskResponse;
import com.smart.smartauth.smartauth.services.AdminService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @DeleteMapping("/delete/all")
    public ResponseEntity<TaskResponse<Object>> deleteUsers(@RequestBody List<Integer> userids) {
        TaskResponse<Object> taskResponse = adminService.deleteAllUsers(userids);
        return ResponseEntity.ok(taskResponse);
    }

    @PatchMapping("/update/role/{userid}/{role}")
    public ResponseEntity<TaskResponse<Object>> updateRole(@PathVariable Integer userid, @PathVariable String role) {
        TaskResponse<Object> taskResponse = adminService.updateRole(userid, role);
        return ResponseEntity.ok(taskResponse);
    }

}
