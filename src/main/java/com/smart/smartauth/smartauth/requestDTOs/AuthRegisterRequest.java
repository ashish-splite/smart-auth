package com.smart.smartauth.smartauth.requestDTOs;

import com.smart.smartauth.smartauth.entities.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRegisterRequest {

    @Valid
    private User user;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()])(?=.*[a-zA-Z]).{8,}$", message = "Password must contain at least one digit, one special character, and be at least 8 characters long")
    private String password;
}
