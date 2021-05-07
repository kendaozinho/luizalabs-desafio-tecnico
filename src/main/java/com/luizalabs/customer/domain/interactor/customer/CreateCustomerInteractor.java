package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;

public interface CreateCustomerInteractor {
  CreateCustomerEndpointResponse execute(CreateCustomerEndpointRequest request);
}
