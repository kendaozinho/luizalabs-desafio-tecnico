package com.luizalabs.customer.application.customerproduct.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerProductInteractorRequest {
  private UUID customerId;
  private UUID productId;
}
