package com.springboot.boilerplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.boilerplate.enitity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
