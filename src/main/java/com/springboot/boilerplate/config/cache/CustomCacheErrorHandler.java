package com.springboot.boilerplate.config.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.data.redis.RedisConnectionFailureException;

public class CustomCacheErrorHandler implements CacheErrorHandler {

	@Override
	public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
		this.handleExceptions(exception);
	}

	@Override
	public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
		this.handleExceptions(exception);
	}

	@Override
	public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
		this.handleExceptions(exception);
	}

	@Override
	public void handleCacheClearError(RuntimeException exception, Cache cache) {
		this.handleExceptions(exception);
	}

	private void handleExceptions(RuntimeException exception) {
		// Treat exceptions as cache miss
		if (exception instanceof RedisConnectionFailureException) {
			System.out.println("Caching exception occurred.");
		}
		return;
	}


}
