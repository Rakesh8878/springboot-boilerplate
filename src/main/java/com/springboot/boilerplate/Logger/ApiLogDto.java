package com.springboot.boilerplate.Logger;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiLogDto {

	private Long logUid;
	private String requestSystem;
	private String userId;
	private Timestamp createdOn;
	private String requestName;
	private String requestParameter;
	private String requestError;
	private String requestHeaders;
	private Integer responseCode;

}
