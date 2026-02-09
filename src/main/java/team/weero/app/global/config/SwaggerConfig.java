package team.weero.app.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info().title("Weero Server API").version("2.0").description("Weero 서버 API 문서"))
        .addSecurityItem(new SecurityRequirement().addList("Bearer Token"))
        .components(
            new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes(
                    "Bearer Token",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description("JWT Bearer Token")));
  }
}
