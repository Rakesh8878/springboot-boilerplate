package com.springboot.boilerplate.service;

import java.util.List;

import com.springboot.boilerplate.dto.UserDto;

public interface UserService {

	UserDto addUser(UserDto userDto);
	List<UserDto> getUsers();
	UserDto getUser(Long id) throws Exception;
	UserDto updateUser(Long id, UserDto userDto) throws Exception;
	String updatePassword(Long id, UserDto userDto) throws Exception;
	String removeUser(Long id) throws Exception;
	
}
