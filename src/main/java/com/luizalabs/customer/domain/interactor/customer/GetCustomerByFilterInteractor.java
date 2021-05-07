package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByFilterEndpointResponse;

import java.util.UUID;

public interface GetCustomerByFilterInteractor {
  GetCustomerByFilterEndpointResponse execute(UUID id, String name, String email);
}
