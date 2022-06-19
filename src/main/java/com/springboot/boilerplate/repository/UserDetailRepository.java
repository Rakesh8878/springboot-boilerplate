package com.springboot.boilerplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.boilerplate.enitity.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

}
