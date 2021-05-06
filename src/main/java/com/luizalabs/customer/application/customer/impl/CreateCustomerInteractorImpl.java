package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.application.customer.CreateCustomerInteractor;
import com.luizalabs.customer.application.customer.request.CreateCustomerInteractorRequest;
import com.luizalabs.customer.application.customer.response.CreateCustomerInteractorResponse;
import com.luizalabs.customer.domain.database.CustomerDatabase;
import com.luizalabs.customer.domain.database.table.CustomerTable;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerInteractorImpl implements CreateCustomerInteractor {
  private CustomerDatabase database;

  public CreateCustomerInteractorImpl(CustomerDatabase database) {
    this.database = database;
  }

  @Override
  public CreateCustomerInteractorResponse execute(CreateCustomerInteractorRequest request) {
    CustomerTable newCustomer = this.database.create(
        new CustomerTable(request.getName(), request.getEmail())
    );

    return new CreateCustomerInteractorResponse(newCustomer.getId(), newCustomer.getName(), newCustomer.getEmail());
  }
}
