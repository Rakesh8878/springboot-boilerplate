package com.springboot.boilerplate.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.boilerplate.dto.UserDto;
import com.springboot.boilerplate.service.UserService;

@Component
public class FetchUserScheduler {

	private static Logger log = LoggerFactory.getLogger(FetchUserScheduler.class);

	@Autowired
	private UserService userService;
	
	@Scheduled(fixedDelay = 1 * 60 * 1000)
	public void synchronizeDataFromServiceCloud() throws Exception {
		List<UserDto> users = userService.getUsers();
		System.out.println(users);
	}

}
