package com.desafio.um.infrastructure.configs;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Desafio 1",
                description = "API para gerenciamento de Feedbacks em Filas FIFO",
                version = "1.0.0"
        )
)
public class OpenAPIConfig {

    @Bean
    public GroupedOpenApi controllers() {
        return GroupedOpenApi.builder()
                .group("Endpoints")
                .packagesToScan("com.desafio.um.presentation.controllers")
                .build();
    }

    @Bean
    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
        return new SwaggerUiConfigParameters(new SwaggerUiConfigProperties());
    }

}

