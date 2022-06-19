package com.springboot.boilerplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.boilerplate.enitity.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
