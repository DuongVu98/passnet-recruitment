package com.iucse.passnet.recruitment.config;

import graphql.kickstart.tools.TypeDefinitionFactory;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.iucse.passnet.recruitment")
@EnableJpaRepositories(basePackages = "com.iucse.passnet.recruitment.domain.repositories")
public class ApplicationConfiguration {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
           .components(new Components())
           .info(new Info()
              .title("Passnet Recruitment API Docs")
              .description("RESTful API documents for Passnet Recruitment service")
           );
    }
}
