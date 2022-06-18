/**
 * 
 */
package com.springboot.boilerplate.controller;

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

/**
 * @author workstation
 * User controller
 */
@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

	@GetMapping("/")
	public String getUsers() {
		return "Here is list of users";
	}
	
	@PostMapping("/")
	public String addUser(@RequestBody String body) {
		return "One user added";
	}
	
	@GetMapping("/{id}")
	public String getUserById(@PathVariable(name = "id", required = true) String id) {
		return "Here is user which you asked for.";
	}
	
	@PutMapping("/{id}")
	public String updateUser(@PathVariable(name = "id", required = true) String id, @RequestBody String body) {
		return "User is updated successfully.";
	}
	
	@PatchMapping("/{id}")
	public String modifyUser(@PathVariable(name = "id", required = true) String id, @RequestBody String body) {
		return "User is modified successfully.";
	}
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable(name = "id", required = true) String id) {
		return "User is deleted successfully.";
	}
	
}
