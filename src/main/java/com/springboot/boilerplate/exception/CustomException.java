package com.springboot.boilerplate.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

private static final long serialVersionUID = 1L;
	
	private transient Object requestBody;
	private transient Object payload; 
	private transient List<ErrorMessage> messages;
	private HttpStatus status;
	
	public CustomException(Object requestBody, Object payload, List<ErrorMessage> messages, HttpStatus status) {
		super("CustomException");
		this.requestBody = requestBody;
		this.payload = payload;
		this.messages = messages;
		this.status = status;
	}
	
	public AppResource<Object> getAppResource() {
		return new AppResource<>(requestBody, payload, messages, status);
	}

}
