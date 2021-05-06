package com.luizalabs.customer.application.customer;

import com.luizalabs.customer.application.customer.response.GetCustomerByIdInteractorResponse;

import java.util.UUID;

public interface GetCustomerByIdInteractor {
  GetCustomerByIdInteractorResponse execute(UUID id);
}
