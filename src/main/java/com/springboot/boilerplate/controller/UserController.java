/**
 * 
 */
package com.springboot.boilerplate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

/**
 * @author workstation
 * User controller
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id", required = true) Long id) throws Exception {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id", required = true) Long id, @RequestBody UserDto userDto) throws Exception {
		return new ResponseEntity<>(userService.updateUser(id, userDto), HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<String> updatePassword(@PathVariable(name = "id", required = true) Long id, @RequestBody UserDto userDto) throws Exception {
		return new ResponseEntity<>(userService.updatePassword(id, userDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable(name = "id", required = true) Long id) throws Exception {
		return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);
	}
	
}
