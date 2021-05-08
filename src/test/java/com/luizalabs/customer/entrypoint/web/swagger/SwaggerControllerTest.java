package com.luizalabs.customer.entrypoint.web.swagger;

import com.luizalabs.customer.entrypoint.base.BaseControllerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SwaggerControllerTest extends BaseControllerTest {
  @Test
  public void redirectWithSuccess() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + super.port + "/swagger", String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Assertions.assertTrue(response.getBody() != null && response.getBody().contains("Swagger UI"));
  }
}