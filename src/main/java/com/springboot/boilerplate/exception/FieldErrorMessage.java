package com.springboot.boilerplate.exception;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data
public class FieldErrorMessage {
	private String field;
    private String error;

    public FieldErrorMessage(FieldError fieldError) {
        this.setField(fieldError.getField());
        this.setError(fieldError.getDefaultMessage());
    }

}
