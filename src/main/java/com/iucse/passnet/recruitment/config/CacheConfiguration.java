package com.iucse.passnet.recruitment.config;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
//@EnableCaching
@EnableRedisRepositories
public class CacheConfiguration implements CachingConfigurer {

	//	@Value("${spring.redis.host}")
	//	private String redisHost;
	//
	//	@Value("${spring.redis.port}")
	//	private int redisPort;

	@Override
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

			@Override
			protected Cache createConcurrentMapCache(final String name) {
				return new ConcurrentMapCache(
					name,
					CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build().asMap(),
					false
				);
			}
		};

		return cacheManager;
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}
}
