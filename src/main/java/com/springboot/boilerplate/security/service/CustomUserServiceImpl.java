package com.springboot.boilerplate.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.boilerplate.enitity.User;
import com.springboot.boilerplate.repository.UserRepository;
import com.springboot.boilerplate.security.dto.AuthenticatedUser;

@Component
public class CustomUserServiceImpl implements CustomUserService {

	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUserName(username);
	}

	@Override
	public AuthenticatedUser findAuthenticatedUserByUsername(String username) {
		User user = findByUsername(username);
		return AuthenticatedUser.builder().name(username).userName(user.getUserName())
				.userRole(user.getRole().toString()).password(user.getPassword()).build();
	}

}
