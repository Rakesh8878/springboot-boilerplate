package com.springboot.boilerplate.config.cache;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import com.springboot.boilerplate.config.AppConfig;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;

public class CacheConfiguration extends CachingConfigurerSupport implements CachingConfigurer {

	@Autowired
	private AppConfig appConfig;
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		// Set socket connection timeout
		final SocketOptions socketOptions = SocketOptions.builder()
				.connectTimeout(Duration.ofSeconds(appConfig.redisSocketTimeoutSeconds)).build();

		final ClientOptions clientOptions = ClientOptions.builder().socketOptions(socketOptions).build();

		// Set command timeout, use SSL encryption, disable peer verification (required for ElastiCache)
		LettuceClientConfiguration clientConfig;
		if (appConfig.redisUseSsl) {
			clientConfig = LettuceClientConfiguration.builder().commandTimeout(Duration.ofSeconds(appConfig.redisTimeoutSeconds))
					.clientOptions(clientOptions).useSsl().disablePeerVerification().build();
		} else {
			clientConfig = LettuceClientConfiguration.builder().commandTimeout(Duration.ofSeconds(appConfig.redisTimeoutSeconds))
					.clientOptions(clientOptions).build();
		}
		RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(appConfig.redisHost, appConfig.redisPort);
	
		// Set password for connecting to Redis
		serverConfig.setPassword(appConfig.redisPassword);

		return new LettuceConnectionFactory(serverConfig, clientConfig);
	}

	private RedisCacheConfiguration defaultRedisCacheConfiguration(Duration ttlPeriod) {
		// Set TTL period
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(ttlPeriod);
		// Set Cache Key Converter
		redisCacheConfiguration.addCacheKeyConverter(new CacheKeyConverter());
		// Adds prefix to cache key, default is CacheName
		redisCacheConfiguration.usePrefix();
		return redisCacheConfiguration;
	}

	@Bean
	public RedisCacheManager redisCacheManager(LettuceConnectionFactory lettuceConnectionFactory) {
		// Default Cache configuration
		RedisCacheConfiguration defaultRedisCacheConfiguration = defaultRedisCacheConfiguration(
				Duration.ofMinutes(appConfig.defaultTtlPeriodMinutes));

		// TTL map for different cache names, can be used to customize TTL for different caches.
		Map<String, RedisCacheConfiguration> cacheNamesConfigurationMap = new HashMap<>();
		// Add cache name with with default ttl 
		cacheNamesConfigurationMap.put("getUsersCache", defaultRedisCacheConfiguration(Duration.ofHours(1)));
		return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory)
				.cacheDefaults(defaultRedisCacheConfiguration)
				.withInitialCacheConfigurations(cacheNamesConfigurationMap).build();

	}

	@Override
	public CacheErrorHandler errorHandler() {
		// Add Custom Error Handler
		return new CustomCacheErrorHandler();
	}
	
}
