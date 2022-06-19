package com.springboot.boilerplate.dto;

import java.util.Date;

import com.springboot.boilerplate.constant.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstructorDto {
	
	private Long id;
	private String name;
	private String email;
	private String phone;
	private Gender gender;
	private Date dateOfBirth;
	
}
