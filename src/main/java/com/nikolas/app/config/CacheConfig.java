package com.nikolas.app.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;

import java.util.ConcurrentModificationException;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public ConcurrentMapCacheManager cacheManager(){
        return new ConcurrentMapCacheManager("my-cache");
    }
}
