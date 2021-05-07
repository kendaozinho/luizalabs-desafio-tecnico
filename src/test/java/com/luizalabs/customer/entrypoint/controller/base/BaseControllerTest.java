package com.luizalabs.customer.entrypoint.controller.base;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {
  @LocalServerPort
  protected Integer port = 0;

  @Autowired
  private WebApplicationContext context;

  protected MockMvc mock;

  @BeforeEach
  private void before() {
    this.mock = MockMvcBuilders.webAppContextSetup(this.context).build();
  }
}
