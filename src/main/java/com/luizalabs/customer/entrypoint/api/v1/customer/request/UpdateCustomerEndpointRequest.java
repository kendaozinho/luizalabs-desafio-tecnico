package com.luizalabs.customer.entrypoint.api.v1.customer.request;

import com.luizalabs.customer.domain.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema
public class UpdateCustomerEndpointRequest {
  @NotNull
  @NotBlank
  @Schema(required = true, name = "Name", example = "Kenneth Gottschalk de Azevedo")
  private String name;

  @NotNull
  @NotBlank
  @Schema(required = true, name = "Email", example = "kendao@luizalabs.com")
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
    customer.setName(this.getName());
    customer.setEmail(this.getEmail());
    return customer;
  }
}
