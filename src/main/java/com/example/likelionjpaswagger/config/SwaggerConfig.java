package com.example.likelionjpaswagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public static final String JWT_SCHEME_NAME = "jwtAuth";

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version("v1.0.0")
                .title("LIKELION JPA Swagger API")
                .description("Spring Data JPA와 Swagger JWT 인증 실습 API");

        SecurityRequirement securityRequirement =
                new SecurityRequirement().addList(JWT_SCHEME_NAME);

        Components components = new Components()
                .addSecuritySchemes(JWT_SCHEME_NAME,
                        new SecurityScheme()
                                .name(JWT_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}