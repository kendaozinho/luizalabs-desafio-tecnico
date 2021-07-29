package com.luizalabs.customer.entrypoint.api.v1.customerproduct.request;

import com.luizalabs.customer.domain.entity.CustomerProduct;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Schema
public class CreateCustomerProductEndpointRequest {
  @NotNull
  @Schema(required = true, name = "Customer ID", example = "1eca2963-7ec4-4808-a850-3cc0fdbcf927")
  private UUID customerId;

  @NotNull
  @Schema(required = true, name = "Product ID", example = "3e7284e5-ee71-4746-851c-6729fb9d1de6")
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
