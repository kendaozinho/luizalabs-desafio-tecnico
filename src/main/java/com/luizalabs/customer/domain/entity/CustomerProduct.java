package com.luizalabs.customer.domain.entity;

import java.util.UUID;

public class CustomerProduct {
  private UUID customerId;
  private UUID productId;

  public CustomerProduct() {
  }

  public CustomerProduct(UUID customerId, UUID productId) {
    this.customerId = customerId;
    this.productId = productId;
  }

  public UUID getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }

  public UUID getProductId() {
    return this.productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }
}
