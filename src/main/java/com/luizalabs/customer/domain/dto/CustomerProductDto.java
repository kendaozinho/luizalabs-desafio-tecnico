package com.luizalabs.customer.domain.dto;

import com.luizalabs.customer.domain.database.table.CustomerProductTable;

import java.util.UUID;

public class CustomerProductDto {
  private UUID customerId;
  private UUID productId;

  public CustomerProductDto() {
  }

  public CustomerProductDto(UUID customerId, UUID productId) {
    this.customerId = customerId;
    this.productId = productId;
  }

  public UUID getCustomerId() {
    return this.customerId;
  }

  public UUID getProductId() {
    return this.productId;
  }

  public CustomerProductTable toTable() {
    return new CustomerProductTable(this.customerId, this.productId);
  }
}
