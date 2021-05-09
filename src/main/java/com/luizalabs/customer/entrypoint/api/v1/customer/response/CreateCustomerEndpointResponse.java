package com.luizalabs.customer.entrypoint.api.v1.customer.response;

import java.util.UUID;

public class CreateCustomerEndpointResponse {
  private UUID id;
  private String name;
  private String email;

  public CreateCustomerEndpointResponse() {
  }

  public CreateCustomerEndpointResponse(UUID id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }
}
