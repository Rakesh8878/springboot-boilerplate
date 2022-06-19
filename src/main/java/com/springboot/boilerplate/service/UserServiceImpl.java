package com.springboot.boilerplate.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.boilerplate.Mapper.UserDetailMapper;
import com.springboot.boilerplate.Mapper.UserMapper;
import com.springboot.boilerplate.dto.UserDto;
import com.springboot.boilerplate.enitity.User;
import com.springboot.boilerplate.enitity.UserDetail;
import com.springboot.boilerplate.repository.UserDetailRepository;
import com.springboot.boilerplate.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDto addUser(UserDto userDto) {
		User userToSave = userMapper.mapToUser(userDto);
		User user = userRepository.save(userToSave);
		return userMapper.mapFromUser(user);
	}

	@Override
	@Cacheable(value = "getUsersCache")
	public List<UserDto> getUsers() {
		List<UserDto> userDtos = new ArrayList<>();
		List<User> users = userRepository.findAll();
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			User user = itr.next();
			userDtos.add(userMapper.mapFromUser(user));
		}
		System.out.println("Redis Enabled");
		return userDtos;
	}

	@Override
	public UserDto getUser(Long id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return userMapper.mapFromUser(user.get());
		} else {
			throw new Exception("No user found");
		}
	}

	@Override
	public UserDto updateUser(Long id, UserDto userDto) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			User userFromDb = user.get();
			userFromDb.setEmail(userDto.getEmail());
			userFromDb.setUserName(userDto.getUserName());
			userFromDb.setRole(userDto.getRole());
			User updatedUser = userRepository.save(userFromDb);
			return userMapper.mapFromUser(updatedUser);
		} else {
			throw new Exception("No user found");
		}
	}

	@Override
	public String updatePassword(Long id, UserDto userDto) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			User userFromDb = user.get();
			userFromDb.setPassword(userDto.getPassword());
			userRepository.save(userFromDb);
			return "Password updated successfully";
		} else {
			throw new Exception("No user found");
		}
	}

	@Override
	public String removeUser(Long id) throws Exception {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			User userFromDb = user.get();
			userRepository.delete(userFromDb);
			return "User deleted successfully";
		} else {
			throw new Exception("No user found");
		}
	}

}
