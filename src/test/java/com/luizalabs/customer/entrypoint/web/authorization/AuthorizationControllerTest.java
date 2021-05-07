package com.luizalabs.customer.entrypoint.web.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizalabs.customer.domain.exception.dto.BaseResponseError;
import com.luizalabs.customer.entrypoint.controller.base.BaseControllerTest;
import com.luizalabs.customer.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class AuthorizationControllerTest extends BaseControllerTest {
  @Autowired
  private ObjectMapper mapper;

  @Value("${spring.application.jwt.secret-key}")
  private String jwtSecretKey;

  @Test
  public void routeIsUnauthorized() throws Throwable {
    try {
      RestTemplate restTemplate = new RestTemplate();
      restTemplate.getForEntity("http://localhost:" + super.port + "/abc", String.class);
      Assertions.fail();
    } catch (HttpStatusCodeException e) {
      Assertions.assertEquals(e.getStatusCode(), HttpStatus.UNAUTHORIZED);

      BaseResponseError response = this.mapper.readValue(e.getResponseBodyAsString(), BaseResponseError.class);

      Assertions.assertNotNull(response);
      Assertions.assertEquals(response.getCode(), HttpStatus.UNAUTHORIZED.value());
      Assertions.assertEquals(response.getMessage(), "Unauthorized");
      Assertions.assertEquals(response.getDetails(), "Authorization is empty or invalid");
    }
  }

  @Test
  public void routeIsAuthorized() {
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<String> httpEntity = new HttpEntity<>(
        new HttpHeaders() {{
          add("Authorization", "Bearer " + JwtUtil.getEncodedJwt(jwtSecretKey));
        }}
    );

    try {
      restTemplate.exchange("http://localhost:" + super.port + "/abc", HttpMethod.GET, httpEntity, String.class);
      Assertions.fail();
    } catch (HttpStatusCodeException e) {
      Assertions.assertNotEquals(e.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }
  }
}