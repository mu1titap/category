package com.multitab.category.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "Adaptors",
        version = "v1",
        description = "Spharos Academy 5th, team Adapators' MULTITAP like Service API Docs"
    )
)

@SecurityScheme(
    name = "Bearer Auth",
    type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
    bearerFormat = "JWT", scheme = "bearer"
)

@Profile("!prod")
@Configuration
@ComponentScan(basePackages = "com.multitab.category.api")
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        String[] paths = {"/api/v1/**"};
        return GroupedOpenApi.builder()
            .group("public-api")
            .pathsToMatch(paths)
            .build();
    }
}