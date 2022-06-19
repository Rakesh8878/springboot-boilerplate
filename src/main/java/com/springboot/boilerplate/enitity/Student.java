package com.springboot.boilerplate.enitity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id", nullable = false) 
	private String userId;
	
	@ManyToMany
	@JoinTable(name = "registered_course", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = { @JoinColumn(name = "course_id") } )
    Set<Course> registeredCourses;
}
