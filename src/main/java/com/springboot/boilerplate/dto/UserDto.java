package com.springboot.boilerplate.dto;

import javax.validation.constraints.Email;
import com.springboot.boilerplate.constant.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	private Long id;
	private String userName;
	@Email(message = "Provide vaild email")
	private String email;
	private String password;
	private Role role;
	private UserDetailDto userDetailDto;
	
}
