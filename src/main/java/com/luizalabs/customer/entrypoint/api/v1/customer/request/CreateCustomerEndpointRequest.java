package com.luizalabs.customer.entrypoint.api.v1.customer.request;

import com.luizalabs.customer.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerEndpointRequest {
  private String name;
  private String email;

  public Customer toEntity() {
    return Customer.builder().name(this.name).email(this.email).build();
  }
}

