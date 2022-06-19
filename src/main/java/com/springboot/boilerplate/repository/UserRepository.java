package com.springboot.boilerplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.boilerplate.enitity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);
	boolean existsByEmail(String email);
	boolean existsByUserName(String username);
	
}
