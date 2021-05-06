package com.luizalabs.customer.entrypoint.api.v1.customerproduct;

import com.luizalabs.customer.application.customerproduct.request.CreateCustomerProductInteractorRequest;
import com.luizalabs.customer.application.customerproduct.response.CreateCustomerProductInteractorResponse;
import com.luizalabs.customer.application.customerproduct.response.GetCustomerProductInteractorResponse;

import java.util.UUID;

public interface CustomerProductEndpoint {
  GetCustomerProductInteractorResponse get(UUID customerId, UUID productId);

  CreateCustomerProductInteractorResponse post(CreateCustomerProductInteractorRequest request);

  void delete(UUID customerId, UUID productId);
}
