package com.springboot.boilerplate.security.service;

import com.springboot.boilerplate.enitity.User;
import com.springboot.boilerplate.security.dto.AuthenticatedUser;

public interface CustomUserService {

	User findByUsername(String username);
	AuthenticatedUser findAuthenticatedUserByUsername(String username);
	
}
