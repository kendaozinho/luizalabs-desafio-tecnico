package com.luizalabs.customer.entrypoint.api.v1.customerproduct.response;

import java.util.UUID;

public class GetCustomerProductEndpointResponse {
  private UUID customerId;
  private UUID productId;

  public GetCustomerProductEndpointResponse() {
  }

  public GetCustomerProductEndpointResponse(UUID customerId, UUID productId) {
    this.customerId = customerId;
    this.productId = productId;
  }

  public UUID getCustomerId() {
    return this.customerId;
  }

  public UUID getProductId() {
    return this.productId;
  }
}
