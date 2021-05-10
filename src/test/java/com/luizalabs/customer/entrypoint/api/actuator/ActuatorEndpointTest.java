package com.luizalabs.customer.entrypoint.api.actuator;

import com.luizalabs.customer.entrypoint.base.BaseControllerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ActuatorEndpointTest extends BaseControllerTest {
  @Test
  public void healthCheck() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + super.port + "/actuator/health", String.class);
    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Assertions.assertNotNull(response.getBody());
    Assertions.assertEquals(response.getBody(), "{\"status\":\"UP\"}");
  }
}