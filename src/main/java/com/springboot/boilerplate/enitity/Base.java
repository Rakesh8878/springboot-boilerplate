package com.springboot.boilerplate.enitity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Base {

	@Column(name = "created_on_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOnTimestamp;
	
	@Column(name = "created_by_user")
	private String createdByUser;

	@Column(name = "updated_on_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOnTimestamp;
	
	@Column(name = "updated_by_user")
	private String updatedByUser;

}
