package com.springboot.boilerplate.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {
	
	private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String METHOD_ARGUMENT_NOT_VALID_EXCEPTION = "methodArgumentNotValidException";
    private static final String GLOBAL_EXCEPTION_HANDLER = "GlobalExceptionHandler";
	
	@Autowired
	ErrorMessageConfiguration messageConfig;
	
	@Autowired
	private ExceptionLogger exceptionLogger;
	
	/*
     * To handle errors related to invalid use of null objects
     */
    @ExceptionHandler(value = NullPointerException.class)
    public AppResource<String> nullPointerException(NullPointerException e) {
    	log.error("nullPointerException", e);
        return new AppResource<>(null, null, getErrorMassages("nullPointerException", e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * To handle error when given key/value pair does not exists
     */
    @ExceptionHandler(value = NoSuchFieldException.class)
    public AppResource<String> keyNotFound(NoSuchFieldException e) {
        log.error("noSuchFieldException", e);
        return new AppResource<>(null, null, getErrorMassages("noSuchFieldException", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * To handle errors related to not being allowed access to a specific endpoint
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public AppResource<String> accessDeniedException(AccessDeniedException e) {
       log.info("accessDeniedException {}", e.getMessage());
       
        return new AppResource<>(null, null, getErrorMassages("accessDeniedException", e.getMessage(), null), HttpStatus.FORBIDDEN);
    }

    /*
     * To handle errors related to model validation failing
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public AppResource<List<FieldErrorMessage>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("methodArgumentNotValidException" + "{}", e.getMessage());
        List<FieldErrorMessage> fieldErrors = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            fieldErrors.add(new FieldErrorMessage(fieldError));
        }
        return new AppResource<>(null, fieldErrors, getErrorMassages("methodArgumentNotValidException", null), HttpStatus.BAD_REQUEST);
    }

    /*
     * To handle errors related to input validation/ business rules
     */
    @ExceptionHandler(value = ValidationException.class)
    public AppResource<String> inputValidationException(ValidationException e) {
        log.info("validationException" +  "{}", e.getMessage());
        return new AppResource<>(null, null, getErrorMassages("validationException", e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
    
    /*
     * To handle errors related to external http server errors
     */
    @ExceptionHandler(value = HttpServerErrorException.class)
    public AppResource<String> httpServerErrorException(HttpServerErrorException e) {
        log.info("httpServerErrorException" + "{}", e.getMessage());
        return new AppResource<>(null, null, getErrorMassages("httpServerErrorException", e.getMessage(), e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * To handle any unhandled exceptions
     */
    @ExceptionHandler(value = Exception.class)
    public AppResource<String> exception(Exception e) {
        log.error("exception", e);
        return new AppResource<>(null, null, getErrorMassages("exception", e), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
	/*
     * To handle errors missing parameter exception
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public AppResource<String> missingServletRequestParameterException(MissingServletRequestParameterException e) {
    	log.info("missingServletRequestParameterException" + "{}", e.getMessage());
        return new AppResource<>(null, null, getErrorMassages("missingServletRequestParameterException", null, e.getMessage(), e),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /*
     * To handle any custom exception
     */
    @ExceptionHandler(CustomException.class)
    public AppResource<Object> customException(CustomException e) {
    	log.info("customException" + "{}", e.getMessage());
        return e.getAppResource();
    }

    
    private List<ErrorMessage> getErrorMassages(String exception, Exception exceptionObject) {
    	List<ErrorMessage> messages = new ArrayList<>();
    	ErrorMessage message = messageConfig.getMessage(exception);
     	if(exceptionObject != null) message = exceptionLogger.logException(GLOBAL_EXCEPTION_HANDLER + "." + exception, exceptionObject, message, null);
     	messages.add(message);
     	return messages;
    }
    
    private List<ErrorMessage> getErrorMassages(String exception, String msg, Exception exceptionObject) {
    	List<ErrorMessage> messages = new ArrayList<>();
    	ErrorMessage message = messageConfig.getMessage(exception, msg);
     	if(exceptionObject != null) message = exceptionLogger.logException(GLOBAL_EXCEPTION_HANDLER + "." + exception, exceptionObject, message, null);
     	messages.add(message);
     	return messages;
    }
    
    private List<ErrorMessage> getErrorMassages(String exception, String text, String msg, Exception exceptionObject) {
    	List<ErrorMessage> messages = new ArrayList<>();
    	ErrorMessage message = messageConfig.getMessage(exception, text, msg);
     	if(exceptionObject != null) message = exceptionLogger.logException(GLOBAL_EXCEPTION_HANDLER + "." + exception, exceptionObject, message, null);
     	messages.add(message);
     	return messages;
    }

}
