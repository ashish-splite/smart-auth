package com.smart.smartauth.smartauth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.smartauth.smartauth.entities.PasswordMapping;
import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.repositories.PasswordMappingRepository;
import com.smart.smartauth.smartauth.repositories.UserRepository;
import com.smart.smartauth.smartauth.requestDTOs.AuthRegisterRequest;
import com.smart.smartauth.smartauth.requestDTOs.AuthSignInRequest;
import com.smart.smartauth.smartauth.requestDTOs.AuthValidateRequest;
import com.smart.smartauth.smartauth.responseDTOs.AuthSignInResponse;
import com.smart.smartauth.smartauth.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordMappingRepository passwordMappingRepository;

    @Autowired
    private JwtService jwtService;

    public AuthSignInResponse register(AuthRegisterRequest authRegisterRequest) {

        String username = authRegisterRequest.getUser().getUsername();
        String password = authRegisterRequest.getPassword();

        if (userRepository.findByUsername(username).isPresent())
            return new AuthSignInResponse(false, "Username already exists", "", null);

        PasswordMapping passwordMapping = new PasswordMapping(username, password);
        passwordMappingRepository.save(passwordMapping);

        User savedUser = userRepository.save(authRegisterRequest.getUser());
        String jwtToken = jwtService.generateToken(username);

        return new AuthSignInResponse(true, "Successfully registered", jwtToken, savedUser);

    }

    public AuthSignInResponse signIn(AuthSignInRequest authSignInRequest) {

        String username = authSignInRequest.getUsername();
        String password = authSignInRequest.getPassword();

        Optional<PasswordMapping> passwordMapping = passwordMappingRepository.findByUsername(username);

        if (!passwordMapping.isPresent() || !password.equals(passwordMapping.get().getPassword())) {
            return new AuthSignInResponse(false, "Incorrect username or password", "", null);
        }

        String jwtToken = jwtService.generateToken(username);
        User user = userRepository.findByUsername(username).get();

        return new AuthSignInResponse(true, "Successfully signed in", jwtToken, user);

    }

    public AuthSignInResponse validate(AuthValidateRequest authValidateRequest) {
        String jwtToken = authValidateRequest.getJwtToken();

        try {
            String username = jwtService.extractUsername(jwtToken);
            User user = userRepository.findByUsername(username).get();
            return new AuthSignInResponse(true, "Validated successful", jwtToken, user);

        } catch (Exception e) {

            return new AuthSignInResponse(false, "Validation failed", "", null);
        }

    }
}
