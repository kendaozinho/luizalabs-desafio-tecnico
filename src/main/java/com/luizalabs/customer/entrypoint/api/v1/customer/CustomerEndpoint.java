package com.luizalabs.customer.entrypoint.api.v1.customer;

import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;

import java.util.ArrayList;
import java.util.UUID;

public interface CustomerEndpoint {
  ArrayList<GetCustomerEndpointResponse> getAll(UUID id, String name, String email, Integer offset, Integer limit);

  GetCustomerEndpointResponse getById(UUID id);

  CreateCustomerEndpointResponse post(CreateCustomerEndpointRequest request);

  UpdateCustomerEndpointResponse put(UUID id, UpdateCustomerEndpointRequest request);

  void delete(UUID id);
}
