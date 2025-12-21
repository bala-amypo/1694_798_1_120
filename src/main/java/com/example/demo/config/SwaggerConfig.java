@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Rate Limiter & Quota Manager")
                        .version("1.0.0")
                        .description("Manages API keys, quota plans, usage logs, and rate limits")
                );
    }
}
