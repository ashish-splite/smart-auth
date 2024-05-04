package com.smart.smartauth.smartauth.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.smartauth.smartauth.common.JwtService;
import com.smart.smartauth.smartauth.entities.PasswordMapping;
import com.smart.smartauth.smartauth.entities.RoleUserMapping;
import com.smart.smartauth.smartauth.entities.User;
import com.smart.smartauth.smartauth.repositories.PasswordMappingRepository;
import com.smart.smartauth.smartauth.repositories.RoleUserMappingRepository;
import com.smart.smartauth.smartauth.repositories.UserRepository;
import com.smart.smartauth.smartauth.requestDTOs.AuthRegisterRequest;
import com.smart.smartauth.smartauth.requestDTOs.AuthSignInRequest;
import com.smart.smartauth.smartauth.requestDTOs.AuthValidateRequest;
import com.smart.smartauth.smartauth.responseDTOs.AuthSignInResponse;
import com.smart.smartauth.smartauth.services.AuthService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordMappingRepository passwordMappingRepository;

    @Autowired
    private RoleUserMappingRepository roleUserMappingRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthSignInResponse register(AuthRegisterRequest authRegisterRequest) {

        String username = authRegisterRequest.getUser().getUsername();
        String password = passwordEncoder.encode(authRegisterRequest.getPassword());

        if (!userRepository.findByUsername(username).isEmpty())
            throw new IllegalArgumentException("Username already exists");

        User savedUser = userRepository.save(authRegisterRequest.getUser());
        String jwtToken = jwtService.generateToken(username);

        PasswordMapping passwordMapping = new PasswordMapping(savedUser.getId(), password);
        passwordMappingRepository.save(passwordMapping);

        RoleUserMapping roleUserMapping = new RoleUserMapping(savedUser.getId(), "USER");
        roleUserMappingRepository.save(roleUserMapping);

        return new AuthSignInResponse(savedUser, jwtToken);

    }

    @Override
    public AuthSignInResponse signIn(AuthSignInRequest authSignInRequest) {

        String username = authSignInRequest.getUsername();
        String password = authSignInRequest.getPassword();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));
        PasswordMapping passwordMapping = passwordMappingRepository.findByUserid(user.getId()).get();

        if (!passwordEncoder.matches(password, passwordMapping.getPassword())) {
            throw new EntityNotFoundException("Password is incorrect");
        }

        String jwtToken = jwtService.generateToken(username);

        return new AuthSignInResponse(user, jwtToken);

    }

    @Override
    public AuthSignInResponse validate(AuthValidateRequest authValidateRequest) {
        String jwtToken = authValidateRequest.getJwtToken();
        String username = jwtService.extractUsername(jwtToken);

        String newJwtToken = jwtService.generateToken(username);
        User user = userRepository.findByUsername(username) .orElseThrow(() -> new EntityNotFoundException("User doesn't exist"));

        return new AuthSignInResponse(user, newJwtToken);

    }

    @Override
    public void isUsernameExist(String username) {
        if (userRepository.findByUsername(username).isEmpty())
            throw new IllegalArgumentException("Username doesn't exist");
    }
}
