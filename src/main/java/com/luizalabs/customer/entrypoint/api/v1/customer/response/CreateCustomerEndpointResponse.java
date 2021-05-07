package com.luizalabs.customer.entrypoint.api.v1.customer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerEndpointResponse {
  private UUID id;
  private String name;
  private String email;
}
