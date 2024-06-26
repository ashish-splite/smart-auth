package com.smart.smartauth.smartauth.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.smartauth.smartauth.annotations.ApiKeyRequired;
import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.requestDTOs.UserIds;
import com.smart.smartauth.smartauth.services.ISCService;

@RestController
@CrossOrigin(origins = "*")
@ApiKeyRequired
@RequestMapping("/isc")
public class ISCController {
    @Autowired
    private ISCService iscService;

    @PostMapping("/fetch/all")
    public ResponseEntity<List<User>> fetchUsers(@RequestBody UserIds userIds) {

        List<User> users = iscService.fetchAllUsers(userIds);
        return ResponseEntity.ok(users);
    }

}
