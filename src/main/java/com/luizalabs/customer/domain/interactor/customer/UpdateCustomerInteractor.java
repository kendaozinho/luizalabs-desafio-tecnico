package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;

import java.util.UUID;

public interface UpdateCustomerInteractor {
  UpdateCustomerEndpointResponse execute(UUID id, UpdateCustomerEndpointRequest request);
}
