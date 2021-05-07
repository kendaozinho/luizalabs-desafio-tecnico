package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByIdEndpointResponse;

import java.util.UUID;

public interface GetCustomerByIdInteractor {
  GetCustomerByIdEndpointResponse execute(UUID id);
}
