package com.springboot.boilerplate.Mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.springboot.boilerplate.dto.UserDetailDto;
import com.springboot.boilerplate.enitity.UserDetail;

@Component
public class UserDetailMapper {

	public UserDetail mapToUserDetail(UserDetailDto userDetailDto) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		try {
			return UserDetail.builder().firstName(userDetailDto.getFirstName()).middleName(userDetailDto.getMiddleName()).lastName(userDetailDto.getLastName())
					.phone(userDetailDto.getPhone()).gender(userDetailDto.getGender()).dateOfBirth(formatter.parse(userDetailDto.getDateOfBirth())).build();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public UserDetailDto mapFromUserDetail(UserDetail userDetail) {
		return UserDetailDto.builder().id(userDetail.getId()).firstName(userDetail.getFirstName()).middleName(userDetail.getMiddleName()).lastName(userDetail.getLastName())
				.phone(userDetail.getPhone()).gender(userDetail.getGender()).dateOfBirth(userDetail.getDateOfBirth().toString()).build();
	}
	
}
