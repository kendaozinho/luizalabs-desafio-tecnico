package com.luizalabs.customer.entrypoint.api.v1.customer.request;

import com.luizalabs.customer.domain.entity.Customer;

public class UpdateCustomerEndpointRequest {
  private String name;
  private String email;

  public UpdateCustomerEndpointRequest() {
  }

  public UpdateCustomerEndpointRequest(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public Customer toEntity() {
    Customer customer = new Customer();
    customer.setName(this.name);
    customer.setEmail(this.email);
    return customer;
  }
}
