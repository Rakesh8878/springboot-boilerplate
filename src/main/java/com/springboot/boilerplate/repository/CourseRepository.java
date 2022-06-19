package com.springboot.boilerplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.boilerplate.enitity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
