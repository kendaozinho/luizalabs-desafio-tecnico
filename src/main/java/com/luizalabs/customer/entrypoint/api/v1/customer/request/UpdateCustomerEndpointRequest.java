package com.luizalabs.customer.entrypoint.api.v1.customer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerEndpointRequest {
  private String name;
  private String email;
}
