package com.spring.fileupload.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class SwaggerConfig {
    @Bean
    @ConditionalOnProperty(prefix = "swagger", name = "service", havingValue = "true")
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(apiInfo())
                .components(new Components().addSecuritySchemes(securitySchemeName, securityScheme()))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }

    private Info apiInfo() {
        return new Info().title("File Uploader").version("1.0.0");
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");
    }
}
