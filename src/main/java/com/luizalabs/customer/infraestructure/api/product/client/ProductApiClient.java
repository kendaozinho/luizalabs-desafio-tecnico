package com.luizalabs.customer.infraestructure.api.product.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class ProductApiClient {
  private final RestTemplateBuilder restTemplateBuilder;
  private final String baseUrl;
  private final Integer timeout;

  public ProductApiClient(
      RestTemplateBuilder restTemplateBuilder,
      @Value("${spring.application.client.product.url}") String baseUrl,
      @Value("${spring.application.client.product.timeout}") Integer timeout
  ) {
    this.restTemplateBuilder = restTemplateBuilder;
    this.baseUrl = baseUrl;
    this.timeout = timeout;
  }

  public RestTemplate getRestTemplate() {
    return this.restTemplateBuilder
        .rootUri(this.baseUrl)
        .setConnectTimeout(Duration.ofMillis(this.timeout))
        .setReadTimeout(Duration.ofMillis(this.timeout))
        .build();
  }
}
