package com.springboot.boilerplate.security.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
	
	@NotEmpty(message = "User name can not be empty")
	private String username;

	@NotEmpty(message = "User password can not be empty")
	private String password;
	
}
