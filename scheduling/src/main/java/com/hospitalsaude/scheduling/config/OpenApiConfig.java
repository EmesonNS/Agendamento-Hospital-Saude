package com.hospitalsaude.scheduling.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        final String securitySchemeName = "Bearer Authentication";
        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .description("Token JWT obtido no endpoint login");

        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName, securityScheme)
                )
                .addSecurityItem(
                        new SecurityRequirement().addList(securitySchemeName)
                )
                .info(
                        new Info()
                                .title("API de Agendamento - Hospital Saúde")
                                .version("V1")
                                .description("API REST para gerenciamento de agendamentos de pacientes e médicos")
                );
    }
}
