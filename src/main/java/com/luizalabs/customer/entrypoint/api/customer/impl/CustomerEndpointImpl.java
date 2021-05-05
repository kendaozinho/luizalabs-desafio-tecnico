package com.luizalabs.customer.entrypoint.api.customer.impl;

import com.luizalabs.customer.entrypoint.api.customer.CustomerEndpoint;
import com.luizalabs.customer.entrypoint.api.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.customer.response.GetCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.customer.response.UpdateCustomerEndpointResponse;

import java.util.UUID;

public class CustomerEndpointImpl implements CustomerEndpoint {
  @Override
  public GetCustomerEndpointResponse get(UUID id) {
    return null;
  }

  @Override
  public CreateCustomerEndpointResponse post(CreateCustomerEndpointRequest request) {
    return null;
  }

  @Override
  public UpdateCustomerEndpointResponse put(UUID id, UpdateCustomerEndpointRequest request) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
