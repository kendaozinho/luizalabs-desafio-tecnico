package com.luizalabs.customer.domain.interactor.customerproduct;

import com.luizalabs.customer.domain.entity.CustomerProduct;

import java.util.UUID;

public interface GetCustomerProductInteractor {
  CustomerProduct execute(UUID customerId, UUID productId);
}
