package com.luizalabs.customer.entrypoint.api.v1.customer.request;

import com.luizalabs.customer.domain.entity.Customer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
public class UpdateCustomerEndpointRequest {
  @NotNull
  @NotBlank
  @ApiModelProperty(required = true, value = "Name", example = "Kenneth Gottschalk de Azevedo")
  private String name;

  @NotNull
  @NotBlank
  @ApiModelProperty(required = true, value = "Email", example = "kendao@luizalabs.com")
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
