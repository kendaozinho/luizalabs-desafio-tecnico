package com.luizalabs.customer.domain.interactor.customerproduct;

import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.GetCustomerProductEndpointResponse;

import java.util.UUID;

public interface GetCustomerProductInteractor {
  GetCustomerProductEndpointResponse execute(UUID customerId, UUID productId);
}
