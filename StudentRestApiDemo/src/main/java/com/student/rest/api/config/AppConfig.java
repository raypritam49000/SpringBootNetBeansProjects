package com.student.rest.api.config;

import com.student.rest.api.cache.SimpleCacheManager;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SimpleCacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        return cacheManager;
    }
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
