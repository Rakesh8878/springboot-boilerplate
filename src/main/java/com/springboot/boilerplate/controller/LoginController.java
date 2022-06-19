package com.springboot.boilerplate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.boilerplate.security.dto.LoginRequest;
import com.springboot.boilerplate.security.dto.LoginResponse;
import com.springboot.boilerplate.security.service.JwtTokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@CrossOrigin
@RestController
@RequestMapping
@Tag(name = "Login", description = "Login API")
public class LoginController {

	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Operation(summary = "Api to log in")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Authenticate user against username and pssword.",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)) }
			),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})
	@PostMapping(value = "/authenticate")
	public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
		try {
			Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
			authenticationManager.authenticate(authentication);
		} catch (BadCredentialsException ex) {
			throw new Exception("Invalid credential", ex);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
		final String token = jwtTokenService.generateToken(userDetails);
		return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
	}
	
}
