package com.springboot.boilerplate.Logger;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "api_log")
public class ApiLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_uid")
	private Long uid;
	
	@Column(name = "request_system")
	private String requestSystem;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "created_on")
	private Timestamp createdOn;
	
	@Column(name = "request_name")
	private String requestName;
	
	@Column(name = "request_parameter", length = 500)
	private String requestParameter;
	
	@Column(name = "request_error", length = 1000)
	private String requestError;
	
	@Column(name = "request_headers", length = 2000)
	private String requestHeaders;
	
	@Column(name = "response_code")
	private Integer responseCode;

}
