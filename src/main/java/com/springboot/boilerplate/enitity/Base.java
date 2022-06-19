package com.springboot.boilerplate.enitity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Base {

	@Column(name = "created_on_timestamp", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOnTimestamp;
	
	@Column(name = "created_by_user", nullable = true)
	private String createdByUser;

	@Column(name = "updated_on_timestamp", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOnTimestamp;
	
	@Column(name = "updated_by_user", nullable = true)
	private String updatedByUser;

}
