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

import com.smart.smartauth.smartauth.annotations.AdminKeyRequired;
import com.smart.smartauth.smartauth.annotations.BearerTokenRequired;
import com.smart.smartauth.smartauth.services.AdminService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@BearerTokenRequired
@AdminKeyRequired
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @DeleteMapping("/delete/all")
    public ResponseEntity<Void> deleteUsers(@RequestBody List<Integer> userids) {
         adminService.deleteAllUsers(userids);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/role/{userid}/{role}")
    public ResponseEntity<Void> updateRole(@PathVariable Integer userid, @PathVariable String role) {
        adminService.updateRole(userid, role);
        return ResponseEntity.ok().build();
    }

}
