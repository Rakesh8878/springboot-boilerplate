package com.springboot.boilerplate.dto;

import com.springboot.boilerplate.constant.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailDto {
	
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	private Gender gender;
	private String dateOfBirth;
	
}
