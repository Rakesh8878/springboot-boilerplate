package com.springboot.boilerplate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Configuration()
@NoArgsConstructor
@Getter
public class AppConfig {

	//	Redis
	
	@Value("${spring.redis.host}")
	public String redisHost;

	@Value("${spring.redis.password}")
	public String redisPassword;

	@Value("${spring.redis.port:6379}")
	public int redisPort;

	@Value("${redis.ttl.minutes:60}")
	public int defaultTtlPeriodMinutes;

	@Value("${redis.sockettimeout.secs:1}")
	public int redisSocketTimeoutSeconds;

	@Value("${redis.timeout.secs:1}")
	public int redisTimeoutSeconds;

	@Value("${redis.usessl}")
	public boolean redisUseSsl;

}
