package com.luizalabs.customer.domain.gateway.customerproduct;

import java.util.UUID;

public interface DeleteCustomerProductGateway {
  void delete(UUID customerId, UUID productId);
}
