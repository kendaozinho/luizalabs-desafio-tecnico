package com.luizalabs.customer.application.customerproduct;

import java.util.UUID;

public interface DeleteCustomerProductInteractor {
  void execute(UUID customerId, UUID productId);
}
