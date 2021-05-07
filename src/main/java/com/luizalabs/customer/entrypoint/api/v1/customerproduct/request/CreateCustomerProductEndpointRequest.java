package com.luizalabs.customer.entrypoint.api.v1.customerproduct.request;

import com.luizalabs.customer.domain.entity.CustomerProduct;
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

  public CustomerProduct toEntity() {
    return new CustomerProduct(this.customerId, this.productId);
  }
}
