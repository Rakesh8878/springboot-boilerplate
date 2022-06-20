package com.springboot.boilerplate.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class ErrorResponse<T> {
	
	private T requestBody;
    private T payload;
    private List<ErrorMessage> messages;

}

public class ErrorResponseHandler <T> extends ResponseEntity<ErrorResponse<T>> {
	
	public ErrorResponseHandler() {
        super(new ErrorResponse<>(null, null, null), HttpStatus.OK);
    }

    public ErrorResponseHandler(T requestBody, T payload, List<ErrorMessage> messages) {
        super(new ErrorResponse<>(requestBody, payload, messages), HttpStatus.OK);
    }

    public ErrorResponseHandler(T requestBody, T payload, List<ErrorMessage> messages, HttpStatus status) {
        super(new ErrorResponse<>(requestBody, payload, messages), status);
    }

    public ErrorResponseHandler(HttpStatus status) {
        super(status);
    }

    public ErrorResponseHandler(T requestBody, T payload, List<ErrorMessage> messages, MultiValueMap<String, String> headers,
            HttpStatus status) {
        super(new ErrorResponse<>(requestBody, payload, messages), headers, status);
    }

    public ErrorResponseHandler(MultiValueMap<String, String> headers, HttpStatus status) {
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
