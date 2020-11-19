package com.iucse.passnet.recruitment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
           .allowedOrigins("*")
           .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
           .allowedHeaders("Access-Control-Allow-Origin", "access-control-allow-methods", "access-control-allow-headers", "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization")
           .allowCredentials(false).maxAge(3600);
    }
}
