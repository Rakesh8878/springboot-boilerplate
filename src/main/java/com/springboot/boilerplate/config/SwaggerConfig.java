package com.springboot.boilerplate.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
    	List<Server> servers = new ArrayList<>();
//        final String securitySchemeName = "Bearer Token";
    	Server server = new Server();
        server.setUrl("https://localhost:8080");
        servers.add(server);
        return new OpenAPI()
//                .components(new Components()
//                .addSecuritySchemes(
//                	securitySchemeName,
//                	new SecurityScheme()
//                    	.name(securitySchemeName)
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")
//                        .in(In.HEADER)
//                        .name("Authorization")
//                		)
//                )
                .info(new Info().title("Spring Boot Boilerplate API")
                .description("Restful API documentation of Spring Boot Boilerplate.")
                .version("v0.0.1"))
                .servers(servers);
    }

}
