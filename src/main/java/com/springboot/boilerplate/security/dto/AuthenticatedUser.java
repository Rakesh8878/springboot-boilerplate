package com.springboot.boilerplate.security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AuthenticatedUser {
	
	private String name;

	private String userName;

	private String password;

	private String userRole;
	
}
