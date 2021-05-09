package com.luizalabs.customer.entrypoint.api.v1.customerproduct.request;

import com.luizalabs.customer.domain.entity.CustomerProduct;

import java.util.UUID;

public class CreateCustomerProductEndpointRequest {
  private UUID customerId;
  private UUID productId;

  public CreateCustomerProductEndpointRequest() {
  }

  public CreateCustomerProductEndpointRequest(UUID customerId, UUID productId) {
    this.customerId = customerId;
    this.productId = productId;
  }

  public UUID getCustomerId() {
    return this.customerId;
  }

  public UUID getProductId() {
    return this.productId;
  }

  public CustomerProduct toEntity() {
    return new CustomerProduct(this.getCustomerId(), this.getProductId());
  }
}
