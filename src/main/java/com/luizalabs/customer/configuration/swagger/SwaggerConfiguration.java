package com.luizalabs.customer.configuration.swagger;

import com.luizalabs.customer.domain.enumerable.Environment;
import com.luizalabs.customer.util.ApplicationUtil;
import com.luizalabs.customer.util.JwtUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class SwaggerConfiguration {
  private final UUID jwtSecretKey;
  private final Environment env;

  public SwaggerConfiguration(
      @Value("${spring.application.jwt.secret-key}") UUID jwtSecretKey,
      @Value("${spring.application.env}") Environment env
  ) {
    this.jwtSecretKey = jwtSecretKey;
    this.env = env;
  }

  @Bean
  public GroupedOpenApi getGroupedOpenApi() {
    return GroupedOpenApi.builder()
        .group("default")
        .pathsToMatch("/**")
        .build();
  }

  @Bean
  public OpenAPI getOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Customer API")
                .description(
                    "API to manage a customer's favorite products." +
                        (this.env.equals(Environment.DEVELOPMENT) ? ("\n" + JwtUtil.getEncodedJwt(this.jwtSecretKey.toString())) : "")
                )
                .version(ApplicationUtil.getVersion())
        )
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(
            new Components()
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                        .name("bearerAuth")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        );
  }
}