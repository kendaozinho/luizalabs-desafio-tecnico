package com.luizalabs.customer.application.customer;

import com.luizalabs.customer.application.customer.request.UpdateCustomerInteractorRequest;
import com.luizalabs.customer.application.customer.response.UpdateCustomerInteractorResponse;

import java.util.UUID;

public interface UpdateCustomerInteractor {
  UpdateCustomerInteractorResponse execute(UUID id, UpdateCustomerInteractorRequest request);
}
