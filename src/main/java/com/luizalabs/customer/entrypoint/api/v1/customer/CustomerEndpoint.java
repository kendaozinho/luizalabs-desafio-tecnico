package com.luizalabs.customer.entrypoint.api.v1.customer;

import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByFilterEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByIdEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;

import java.util.UUID;

public interface CustomerEndpoint {
  GetCustomerByFilterEndpointResponse getByFilter(UUID id, String name, String email, Integer offset, Integer limit);

  GetCustomerByIdEndpointResponse getById(UUID id, Boolean expand);

  CreateCustomerEndpointResponse post(CreateCustomerEndpointRequest request);

  UpdateCustomerEndpointResponse put(UUID id, UpdateCustomerEndpointRequest request);

  void delete(UUID id);
}
