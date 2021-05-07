package com.luizalabs.customer.entrypoint.api.v1.customerproduct.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerProductEndpointRequest {
  private UUID customerId;
  private UUID productId;
}
