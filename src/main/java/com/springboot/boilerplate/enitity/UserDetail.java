package com.springboot.boilerplate.enitity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.springboot.boilerplate.constant.Gender;

@Entity
@Table(name = "user_detail")
public class UserDetail extends Base implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name", nullable = false) 
	private String firstName;
	
	@Column(name = "middle_name", nullable = true) 
	private String middleName;
	
	@Column(name = "last_name", nullable = false) 
	private String lastName;
	
	@Column(name = "phone", nullable = false, unique = true) 
	private String phone;
	
	@Column(name = "gender", nullable = false) 
	private Gender gender;
	
	@Column(name = "date_of_birth", nullable = false) 
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
}
