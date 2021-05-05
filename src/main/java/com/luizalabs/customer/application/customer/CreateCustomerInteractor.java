package com.luizalabs.customer.application.customer;

import com.luizalabs.customer.application.customer.request.CreateCustomerInteractorRequest;
import com.luizalabs.customer.application.customer.response.CreateCustomerInteractorResponse;

public interface CreateCustomerInteractor {
  CreateCustomerInteractorResponse execute(CreateCustomerInteractorRequest request);
}
