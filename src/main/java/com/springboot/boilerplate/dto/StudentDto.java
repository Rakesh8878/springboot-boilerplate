package com.springboot.boilerplate.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import com.springboot.boilerplate.constant.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
	
	private Long id;
	private String name;
	@Email(message = "Provide vaild email")
	private String email;
	private String phone;
	private Gender gender;
	private Date dateOfBirth;
	
}
