package com.luizalabs.customer.entrypoint.api.v1.customerproduct;

import com.luizalabs.customer.entrypoint.api.v1.customerproduct.request.CreateCustomerProductEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.CreateCustomerProductEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.GetCustomerProductEndpointResponse;

import java.util.UUID;

public interface CustomerProductEndpoint {
  GetCustomerProductEndpointResponse get(UUID customerId, UUID productId);

  CreateCustomerProductEndpointResponse post(CreateCustomerProductEndpointRequest request);

  void delete(UUID customerId, UUID productId);
}
