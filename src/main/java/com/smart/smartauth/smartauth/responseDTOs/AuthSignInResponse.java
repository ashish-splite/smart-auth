package com.smart.smartauth.smartauth.responseDTOs;

import com.smart.smartauth.smartauth.entities.User;

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
public class AuthSignInResponse {

	private boolean successful;
	private String message;
	private String jwtToken;
	private User user;

}
