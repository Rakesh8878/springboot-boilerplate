package com.springboot.boilerplate.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

	private String code;
	private String text;
	private String detail;
	
	public ErrorMessage(String code, String text) {
		super();
		this.code = code;
		this.text = text;
	}

}
