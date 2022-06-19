package com.springboot.boilerplate.enitity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.springboot.boilerplate.constant.Role;

@Entity
@Table(name = "users")
public class User extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name", nullable = false, unique = true) 
	private String userName;
	
	@Column(name = "email", nullable = false, unique = true) 
	private String email;
	
	@Column(name = "password", nullable = false) 
	private String password;
	
	@Column(name = "role", nullable = false) 
	private Role role;
	
	@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "user")
    private UserDetail userDetail;
	
}
