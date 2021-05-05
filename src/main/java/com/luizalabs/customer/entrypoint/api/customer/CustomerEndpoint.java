package com.luizalabs.customer.entrypoint.api.customer;

import com.luizalabs.customer.entrypoint.api.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.customer.response.GetCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.customer.response.UpdateCustomerEndpointResponse;

import java.util.UUID;

public interface CustomerEndpoint {
  GetCustomerEndpointResponse get(UUID id);

  CreateCustomerEndpointResponse post(CreateCustomerEndpointRequest request);

  UpdateCustomerEndpointResponse put(UUID id, UpdateCustomerEndpointRequest request);

  void delete(UUID id);
}
