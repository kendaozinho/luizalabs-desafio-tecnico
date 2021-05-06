package com.luizalabs.customer.application.customerproduct.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerProductInteractorResponse {
  private UUID customerId;
  private UUID productId;
}
