package com.iucse.passnet.recruitment.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.iucse.passnet.recruitment")
@EnableJpaRepositories(basePackages = "com.iucse.passnet.recruitment.domain.repositories")
public class ApplicationConfiguration {

}
