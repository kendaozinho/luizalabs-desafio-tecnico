package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.application.customer.GetCustomerByIdInteractor;
import com.luizalabs.customer.application.customer.response.GetCustomerByIdInteractorResponse;
import com.luizalabs.customer.domain.database.CustomerDatabase;
import com.luizalabs.customer.domain.database.table.CustomerTable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCustomerByIdInteractorImpl implements GetCustomerByIdInteractor {
  private CustomerDatabase database;

  public GetCustomerByIdInteractorImpl(CustomerDatabase database) {
    this.database = database;
  }

  @Override
  public GetCustomerByIdInteractorResponse execute(UUID id) {
    CustomerTable customer = this.database.findOneById(id);

    return new GetCustomerByIdInteractorResponse(customer.getId(), customer.getName(), customer.getEmail());
  }
}
