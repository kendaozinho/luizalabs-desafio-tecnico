package com.luizalabs.customer.configuration.swagger;

import com.luizalabs.customer.domain.enumerable.Environment;
import com.luizalabs.customer.util.ApplicationUtil;
import com.luizalabs.customer.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.Collections;
import java.util.HashSet;
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
  public UiConfiguration uiConfiguration() {
    return UiConfigurationBuilder.builder()
        .docExpansion(DocExpansion.LIST)
        .defaultModelsExpandDepth(-1) // hide models
        .build();
  }

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .pathMapping("/")
        .apiInfo(this.getApiInfo())
        .forCodeGeneration(true)
        .genericModelSubstitutes(ResponseEntity.class)
        .securityContexts(Collections.singletonList(this.getSecurityContext()))
        .securitySchemes(Collections.singletonList(this.getApiKey()))
        .useDefaultResponseMessages(false)
        .consumes(new HashSet<>() {{
          add("application/json");
        }})
        .produces(new HashSet<>() {{
          add("application/json");
        }})
        .select()
        .paths(PathSelectors.regex("/v[0-9]+/.*"))
        .apis(RequestHandlerSelectors.basePackage("com.luizalabs"))
        .build();
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder()
        .title("Customer API")
        .description(
            "API to manage a customer's favorite products." +
                (this.env.equals(Environment.DEVELOPMENT) ? ("\n" + JwtUtil.getEncodedJwt(this.jwtSecretKey.toString())) : "")
        )
        .version(ApplicationUtil.getVersion())
        .build();
  }

  private SecurityContext getSecurityContext() {
    return SecurityContext.builder()
        .securityReferences(
            Collections.singletonList(
                new SecurityReference(
                    "JWT",
                    Collections.singletonList(
                        new AuthorizationScope("global", "accessEverything")
                    ).toArray(new AuthorizationScope[1])
                )
            )
        )
        .forPaths(PathSelectors.any())
        .build();
  }

  private ApiKey getApiKey() {
    return new ApiKey("JWT", "Authorization", "header");
  }
}