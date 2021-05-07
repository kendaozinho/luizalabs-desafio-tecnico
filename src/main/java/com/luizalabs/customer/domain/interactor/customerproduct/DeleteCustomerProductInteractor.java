package com.luizalabs.customer.domain.interactor.customerproduct;

import java.util.UUID;

public interface DeleteCustomerProductInteractor {
  void execute(UUID customerId, UUID productId);
}
