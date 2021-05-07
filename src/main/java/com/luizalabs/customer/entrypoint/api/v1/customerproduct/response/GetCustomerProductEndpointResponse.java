package com.luizalabs.customer.entrypoint.api.v1.customerproduct.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerProductEndpointResponse {
  private UUID customerId;
  private UUID productId;
}
