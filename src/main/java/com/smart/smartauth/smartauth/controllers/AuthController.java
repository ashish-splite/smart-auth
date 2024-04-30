package com.smart.smartauth.smartauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.smartauth.smartauth.requestDTOs.AuthRegisterRequest;
import com.smart.smartauth.smartauth.requestDTOs.AuthSignInRequest;
import com.smart.smartauth.smartauth.requestDTOs.AuthValidateRequest;
import com.smart.smartauth.smartauth.responseDTOs.AuthSignInResponse;
import com.smart.smartauth.smartauth.services.AuthService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PutMapping("/register")
    public ResponseEntity<AuthSignInResponse> register(@Valid @RequestBody AuthRegisterRequest authRegisterRequest) {

        AuthSignInResponse authSignInResponse = authService.register(authRegisterRequest);
        return ResponseEntity.ok(authSignInResponse);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthSignInResponse> signIn(@RequestBody AuthSignInRequest authSignInRequest) {
        AuthSignInResponse authSignInResponse = authService.signIn(authSignInRequest);
        return ResponseEntity.ok(authSignInResponse);

    }

    @PostMapping("/validate")
    public ResponseEntity<AuthSignInResponse> validate(@RequestBody AuthValidateRequest AuthValidateRequest) {
        AuthSignInResponse authSignInResponse = authService.validate(AuthValidateRequest);
        return ResponseEntity.ok(authSignInResponse);
    }

    @GetMapping("/exists/{username}")
    public ResponseEntity<Void> isUsernameExist(@PathVariable String username) {
       authService.isUsernameExist(username);
       return ResponseEntity.ok().build();
    }
    
}
