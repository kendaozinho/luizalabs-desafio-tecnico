package com.luizalabs.customer.application.customer;

import com.luizalabs.customer.application.customer.response.GetCustomerInteractorResponse;

import java.util.UUID;

public interface GetCustomerInteractor {
  GetCustomerInteractorResponse execute(UUID id);
}
