package com.luizalabs.customer.entrypoint.api.v1.customer;

import com.luizalabs.customer.application.customer.request.CreateCustomerInteractorRequest;
import com.luizalabs.customer.application.customer.request.UpdateCustomerInteractorRequest;
import com.luizalabs.customer.application.customer.response.CreateCustomerInteractorResponse;
import com.luizalabs.customer.application.customer.response.GetCustomerByFilterInteractorResponse;
import com.luizalabs.customer.application.customer.response.GetCustomerByIdInteractorResponse;
import com.luizalabs.customer.application.customer.response.UpdateCustomerInteractorResponse;

import java.util.UUID;

public interface CustomerEndpoint {
  GetCustomerByFilterInteractorResponse getByFilter(UUID id, String name, String email, Integer offset, Integer limit);

  GetCustomerByIdInteractorResponse getById(UUID id);

  CreateCustomerInteractorResponse post(CreateCustomerInteractorRequest request);

  UpdateCustomerInteractorResponse put(UUID id, UpdateCustomerInteractorRequest request);

  void delete(UUID id);
}
