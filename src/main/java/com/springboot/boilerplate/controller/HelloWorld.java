package com.springboot.boilerplate.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HelloWorld {

	@GetMapping(value = "/")
	public String helloWorld() {
		return "Hello World";
	}
}
