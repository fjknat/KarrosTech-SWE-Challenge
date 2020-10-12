/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 12, 2020				1.0				Initialize
 */

@Configuration
@EnableCaching
public class CaffeineConfig {

	@Value("${application.cache.names.gps}")
	String gpsCacheName;
	@Value("${application.cache.names.gps.detail}")
	String gpsDetailCacheName;
	
	@Bean
	public CacheManager cacheManager() {
		CaffeineCacheManager cacheManager = new CaffeineCacheManager(gpsCacheName, gpsDetailCacheName);
		cacheManager.setCaffeine(caffeineCacheBuilder());
		return cacheManager;
	}
	
	@Bean
	Caffeine<Object, Object> caffeineCacheBuilder() {
		return Caffeine.newBuilder().initialCapacity(10).maximumSize(100).expireAfterAccess(10, TimeUnit.MINUTES);
	}
}
