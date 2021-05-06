package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.application.customer.UpdateCustomerInteractor;
import com.luizalabs.customer.application.customer.request.UpdateCustomerInteractorRequest;
import com.luizalabs.customer.application.customer.response.UpdateCustomerInteractorResponse;
import com.luizalabs.customer.domain.database.CustomerDatabase;
import com.luizalabs.customer.domain.database.table.CustomerTable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCustomerInteractorImpl implements UpdateCustomerInteractor {
  private CustomerDatabase database;

  public UpdateCustomerInteractorImpl(CustomerDatabase database) {
    this.database = database;
  }

  @Override
  public UpdateCustomerInteractorResponse execute(UUID id, UpdateCustomerInteractorRequest request) {
    CustomerTable customer = this.database.findOneById(id);

    customer.setName(request.getName());
    customer.setEmail(request.getEmail());

    CustomerTable updatedCustomer = this.database.update(id, customer);

    return new UpdateCustomerInteractorResponse(updatedCustomer.getId(), updatedCustomer.getName(), updatedCustomer.getEmail());
  }
}
