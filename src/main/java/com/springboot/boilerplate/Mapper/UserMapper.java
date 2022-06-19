package com.springboot.boilerplate.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.boilerplate.dto.UserDto;
import com.springboot.boilerplate.enitity.User;

@Component
public class UserMapper {
	
	@Autowired
	UserDetailMapper userDetailMapper;
	
	public User mapToUser(UserDto userDto) {
		return User.builder().userName(userDto.getUserName()).email(userDto.getEmail())
				.password(userDto.getPassword()).role(userDto.getRole()).userDetail(userDetailMapper.mapToUserDetail(userDto.getUserDetailDto())).build();
	}

	public UserDto mapFromUser(User user) {
		return UserDto.builder().id(user.getId()).userName(user.getUserName()).email(user.getEmail()).role(user.getRole())
				.userDetailDto(userDetailMapper.mapFromUserDetail(user.getUserDetail())).build();
	}
	
}
