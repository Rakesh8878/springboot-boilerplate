package com.springboot.boilerplate.constant;

public class PublicUrl {
	
	private PublicUrl() {
		
	}
	
	public static final String[] swaggerArray = new String[]{"/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html","/swagger-resources","/swagger-resources/**","/configuration/ui","/configuration/security","/webjars/**"};
	public static final String[] endpointArray = new String[]{"/", "/authenticate"};

}
