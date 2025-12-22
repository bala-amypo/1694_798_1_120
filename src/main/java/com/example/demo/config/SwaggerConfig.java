package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Rate Limiter & Quota Manager")
                        .version("1.0.0")
                        .description("Manages API keys, quota plans, usage logs, and rate limits")
                )
                .servers(List.of(
                        new Server().url("https://9180.408procr.amypo.ai/")
                ));
    }
}
