package com.iucse.passnet.recruitment.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.iucse.passnet.recruitment")
@EnableJpaRepositories(basePackages = "com.iucse.passnet.recruitment.domain.repositories")
public class ApplicationConfiguration {
	@Value("${view.posted-jobs.id}")
	private String postedJobViewId;

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
			.components(new Components())
			.info(new Info().title("Passnet Recruitment API Docs").description("RESTful API documents for Passnet Recruitment service"));
	}

	@Bean("posted-jobs-view-id")
	public String postedJobsViewId() {
		return this.postedJobViewId;
	}
}
