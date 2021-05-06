package com.luizalabs.customer.application.customer;

import com.luizalabs.customer.application.customer.response.GetCustomerByFilterInteractorResponse;

import java.util.UUID;

public interface GetCustomerByFilterInteractor {
  GetCustomerByFilterInteractorResponse execute(UUID id, String name, String email);
}
