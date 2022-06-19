package com.springboot.boilerplate.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class AppResource <T> extends ResponseEntity<AppResourceInner<T>> {

	
	public AppResource() {
        super(new AppResourceInner<>(null, null, null), HttpStatus.OK);
    }

    public AppResource(T requestBody, T payload, List<ErrorMessage> messages) {
        super(new AppResourceInner<>(requestBody, payload, messages), HttpStatus.OK);
    }

    public AppResource(T requestBody, T payload, List<ErrorMessage> messages, HttpStatus status) {
        super(new AppResourceInner<>(requestBody, payload, messages), status);
    }

    public AppResource(HttpStatus status) {
        super(status);
    }

    public AppResource(T requestBody, T payload, List<ErrorMessage> messages, MultiValueMap<String, String> headers,
            HttpStatus status) {
        super(new AppResourceInner<>(requestBody, payload, messages), headers, status);
    }

    public AppResource(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public T getRequestBody() {
        return this.getBody().getRequestBody();
    }

    public void setRequestBody(T requestBody) {
        this.getBody().setRequestBody(requestBody);
    }

    public T getPayload() {
        return this.getBody().getPayload();
    }

    public void setPayload(T payload) {
        this.getBody().setPayload(payload);
    }

    public List<ErrorMessage> getMessages() {
        return this.getBody().getMessages();
    }

    public void setMessage(List<ErrorMessage> messages) {
        this.getBody().setMessages(messages);
    }

}
