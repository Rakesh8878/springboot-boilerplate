package com.springboot.boilerplate.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExceptionLogger {

	private static Logger log = LoggerFactory.getLogger(ExceptionLogger.class);
	
	public ErrorMessage logException(String methodName, Exception ex, ErrorMessage errorMessage, String data) {
		
		// epoch unix time stamp
		Long epoch = System.currentTimeMillis() / 1000L;
		String uniqueId = epoch.toString();
		
		String errorText = String.format("id=%s, method=%s, %s", uniqueId, methodName, data);
		
		log.error(errorText, ex);
		
		errorMessage.setCode(uniqueId);
		
		return errorMessage;
	}

}
