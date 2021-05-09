package com.luizalabs.customer.infraestructure.api.product.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class ProductApiClient {
  @Value("${spring.application.client.product.url}")
  private String baseUrl;
  @Value("${spring.application.client.product.timeout}")
  private Integer timeout;

  private RestTemplateBuilder restTemplateBuilder;

  public ProductApiClient(
      RestTemplateBuilder restTemplateBuilder
  ) {
    this.restTemplateBuilder = restTemplateBuilder;
  }

  public RestTemplate getRestTemplate() {
    return this.restTemplateBuilder
        .rootUri(this.baseUrl)
        .setConnectTimeout(Duration.ofMillis(this.timeout))
        .setReadTimeout(Duration.ofMillis(this.timeout))
        .build();
  }
}
