package com.springboot.boilerplate.security.service;

import java.util.Collections;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.boilerplate.security.dto.AuthenticatedUser;

import lombok.RequiredArgsConstructor;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	private static final String USERNAME_OR_PASSWORD_INVALID = "Invalid username or password.";

	@Autowired
	private CustomUserService customUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final AuthenticatedUser authenticatedUser = customUserService.findAuthenticatedUserByUsername(username);

		if (Objects.isNull(authenticatedUser)) {
			throw new UsernameNotFoundException(USERNAME_OR_PASSWORD_INVALID);
		}

		final String authenticatedUsername = authenticatedUser.getUserName();
		final String authenticatedPassword = authenticatedUser.getPassword();
		final SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authenticatedUser.getUserRole());

		return new User(authenticatedUsername, authenticatedPassword, Collections.singletonList(grantedAuthority));
		
	}


}
