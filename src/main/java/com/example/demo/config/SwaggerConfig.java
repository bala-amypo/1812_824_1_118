package com.example.demo.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // JWT Bearer Security Scheme
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .info(new Info()
                        .title("Supply Chain Weak Link Analyzer API")
                        .version("1.0"))
                .servers(List.of(
                        new Server().url("https://9016.32procr.amypo.ai/")
                ))
                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                )
                .components(
                        new Components().addSecuritySchemes("bearerAuth", bearerAuth)
                );
    }
}
