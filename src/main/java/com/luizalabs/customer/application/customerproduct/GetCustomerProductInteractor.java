package com.luizalabs.customer.application.customerproduct;

import com.luizalabs.customer.application.customerproduct.response.GetCustomerProductInteractorResponse;

import java.util.UUID;

public interface GetCustomerProductInteractor {
  GetCustomerProductInteractorResponse execute(UUID customerId, UUID productId);
}
