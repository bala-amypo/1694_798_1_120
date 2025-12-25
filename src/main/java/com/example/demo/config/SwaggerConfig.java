@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("https://9219.pro604cr.amypo.ai");

        return new OpenAPI()
                .info(new Info()
                        .title("API Rate Limiter & Quota Manager")
                        .version("1.0.0")
                        .description("Manages API keys, quota plans, usage logs, and rate limits")
                )
                .components(new Components()
                        .addSecuritySchemes("BearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .servers(List.of(server));
    }
}
