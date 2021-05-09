package com.luizalabs.customer.entrypoint.api.v1.customer.response;

import java.util.UUID;

public class UpdateCustomerEndpointResponse {
  private UUID id;
  private String name;
  private String email;

  public UpdateCustomerEndpointResponse() {
  }

  public UpdateCustomerEndpointResponse(UUID id, String name, String email) {
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
