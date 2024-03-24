package com.smart.smartauth.smartauth.controllers;

import org.springframework.beans.factory.annotation.Autowired;

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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
	private AuthService authService;

	@PutMapping("/register")
	public AuthSignInResponse register(@RequestBody AuthRegisterRequest authRegisterRequest) {

		return authService.register(authRegisterRequest);

	}

	@PostMapping("/signin")
	public AuthSignInResponse signIn(@RequestBody AuthSignInRequest authSignInRequest) {

		return authService.signIn(authSignInRequest);

	}
	
	@PostMapping("/validate")
	public AuthSignInResponse validate(@RequestBody AuthValidateRequest AuthValidateRequest) {
		return authService.validate(AuthValidateRequest);
	}
}


