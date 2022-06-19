package com.springboot.boilerplate.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResourceInner<T> {
	
	private T requestBody;
    private T payload;
    private List<ErrorMessage> messages;

}
