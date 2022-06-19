package com.springboot.boilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootBoilerplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBoilerplateApplication.class, args);
	}

}
