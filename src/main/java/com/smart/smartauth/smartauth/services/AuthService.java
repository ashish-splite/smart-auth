package com.smart.smartauth.smartauth.services;

import com.smart.smartauth.smartauth.requestDTOs.AuthRegisterRequest;
import com.smart.smartauth.smartauth.requestDTOs.AuthSignInRequest;
import com.smart.smartauth.smartauth.requestDTOs.AuthValidateRequest;
import com.smart.smartauth.smartauth.responseDTOs.AuthSignInResponse;

public interface AuthService {
    public AuthSignInResponse register(AuthRegisterRequest authRegisterRequest);
    public AuthSignInResponse signIn(AuthSignInRequest authSignInRequest);
    public AuthSignInResponse validate(AuthValidateRequest authValidateRequest);
    public void isUsernameExist(String username);
}
