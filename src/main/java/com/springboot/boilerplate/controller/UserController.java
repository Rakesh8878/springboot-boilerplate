/**
 * 
 */
package com.springboot.boilerplate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.boilerplate.dto.UserDto;
import com.springboot.boilerplate.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author workstation
 * User controller
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/users")
@Tag(name = "User", description = "User API")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Operation(summary = "Api to fetch list of user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetch list of users",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
			),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> getUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
	
	@Operation(summary = "Api to add user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Save user in the database.",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
			),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Api to get user detail")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Get user detail by id",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
			),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id", required = true) Long id) throws Exception {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}
	
	@Operation(summary = "Api to upadte user detail")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update user detail by id",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
			),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id", required = true) Long id, @Valid @RequestBody UserDto userDto) throws Exception {
		return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
	}
	
	@Operation(summary = "Api to update user password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update user password",
					content = { @Content(mediaType = "application/json") }
			),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
	})
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> updatePassword(@PathVariable(name = "id", required = true) Long id, @RequestBody UserDto userDto) throws Exception {
		return new ResponseEntity<>(userService.updatePassword(id, userDto), HttpStatus.OK);
	}

	@Operation(summary = "Api to delete user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Delete user by id",
					content = { @Content(mediaType = "application/json") }
			),
			@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content),
			@ApiResponse(responseCode = "403", description = "Invalid credential", content = @Content),
	})
	@DeleteMapping(value = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> deleteUser(@PathVariable(name = "id", required = true) Long id) throws Exception {
		return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);
	}
	
}
