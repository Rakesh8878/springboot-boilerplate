package com.springboot.boilerplate.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class ApiLogger {
	
	private static Logger log = LoggerFactory.getLogger(ApiLogger.class);
	
	@Autowired
    private RequestAsynchronousLoggingServiceImpl asyncLoggingService;
    
    private String convertBodyToString(byte[] content, String contentEncoding) {
        if (content.length == 0) {
            return "";
        }
        try {
            return new String(content, contentEncoding);
        } catch (UnsupportedEncodingException e) {
            return "Unsupported encoding [" + content.length + " bytes content]";
        }
    }

    public void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {    	
    	String apiPath = getRequestPath(request);
        String userAgent = getRequestHeaderValue(request, "User-Agent", 200);
        String requestSystem = getRequestHeaderValue(request, "Request-System", 200);
        String inboundPayload;
        String outboundPayload;
        try {
            inboundPayload = extractRequestBodyBytes(request);
            outboundPayload = extractResponseBodyBytes(response);
        } catch (Exception e) {
            log.info(e.getMessage());
            return;
        }
        Timestamp functionStartTimestamp = getRequestStartTime(request);
        int elapsedTime = getElapsedTime(functionStartTimestamp);
        int responseCode = getResponseCode(response);
        String requestError = getRequestError(responseCode, outboundPayload);
        String requestHeaders = getRequestHeaders(request);
        
        String userId = null;
        try {
        	if(apiPath.equals("/login") || apiPath.equals("/authenticate")) {
        		// Get from request payload
        		JsonObject inputData = JsonParser.parseString(inboundPayload).getAsJsonObject();
        		userId = URLDecoder.decode(inputData.get("username").getAsString(), "UTF-8" );
            } else {
            	// Get from security context
            	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            	if(authentication != null) {
            		Object principal = authentication.getPrincipal();
            		UserDetails userDetails = (UserDetails) principal;
            		userId = userDetails.getUsername();
            	}
            }
        } catch(Exception e) {
        	log.info(e.getMessage());
        }

        ApiLogDto loggingDto = new ApiLogDto();
        loggingDto.setRequestSystem(requestSystem != null ? requestSystem : userAgent);
        loggingDto.setUserId(userId);
        loggingDto.setCreatedOn(functionStartTimestamp);
        loggingDto.setRequestName(apiPath);
        loggingDto.setRequestParameter(inboundPayload);
        loggingDto.setRequestError(requestError);
        loggingDto.setResponseCode(responseCode);
        loggingDto.setRequestHeaders(requestHeaders);
        
        asyncLoggingService.logRequest(loggingDto);
    }
    
    private String getRequestHeaders(ContentCachingRequestWrapper request) {
    	Enumeration<String> headerNames = request.getHeaderNames();
    	Map<String, String> headers = new HashMap<>();
    	if (headerNames != null) {
        	while (headerNames.hasMoreElements()) {
            	String headerName = headerNames.nextElement();
            	headers.put(headerName, request.getHeader(headerName));
        	}
        }
    	Gson gson = new Gson(); 
    	return gson.toJson(headers); 
    }
    
    private String getRequestError(int responseCode, String body) {
    	return HttpStatus.valueOf(responseCode).isError() ? body : null;
    }
    
    private int getResponseCode(ContentCachingResponseWrapper response) {
    	return response.getStatus();
    }
    
    private int getElapsedTime(Timestamp startTime) {
    	Long endTime = System.currentTimeMillis();
    	return (int) (endTime - startTime.getTime());
    }

    private String getRequestPath(ContentCachingRequestWrapper request) {
        String queryString = request.getQueryString();
        if (queryString == null) {
            return request.getRequestURI();
        } else {
            return request.getRequestURI() + "?" + queryString;
        }
    }

    private String getRequestHeaderValue(ContentCachingRequestWrapper request, String headerName, int maxLength) {
        String headerValue = request.getHeader(headerName);
        if (headerValue != null) {
            return headerValue.substring(0, Math.min(maxLength, headerValue.length()));
        }
        return null;
    }

    private String extractRequestBodyBytes(ContentCachingRequestWrapper request) {
    	return convertBodyToString(request.getContentAsByteArray(), request.getCharacterEncoding());
    }
    
    private String extractResponseBodyBytes(ContentCachingResponseWrapper response) {
        return convertBodyToString(response.getContentAsByteArray(), response.getCharacterEncoding()); 
    }

    private Timestamp getRequestStartTime(ContentCachingRequestWrapper request) {
        Object startTimeObj = request.getAttribute("startTime");
        if (startTimeObj == null || !(startTimeObj instanceof Long)) {
            return new Timestamp(0L);
        }
        return new Timestamp((Long) startTimeObj);
    }

}
