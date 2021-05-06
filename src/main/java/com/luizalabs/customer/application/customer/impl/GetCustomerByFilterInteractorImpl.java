package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.application.customer.GetCustomerByFilterInteractor;
import com.luizalabs.customer.application.customer.response.GetCustomerByFilterInteractorResponse;
import com.luizalabs.customer.domain.database.CustomerDatabase;
import com.luizalabs.customer.domain.database.table.CustomerTable;
import com.luizalabs.customer.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class GetCustomerByFilterInteractorImpl implements GetCustomerByFilterInteractor {
  private CustomerDatabase database;

  public GetCustomerByFilterInteractorImpl(CustomerDatabase database) {
    this.database = database;
  }

  @Override
  public GetCustomerByFilterInteractorResponse execute(UUID id, String name, String email) {
    ArrayList<CustomerTable> customers = new ArrayList<>();

    if (id != null) {
      customers.add(this.database.findOneById(id));
    } else if (email != null) {
      customers.add(this.database.findOneByEmail(email));
    } else if (name != null) {
      customers.addAll(this.database.findAllByName(name));
    } else {
      customers.addAll(this.database.findAll());
    }

    if (customers.isEmpty()) {
      throw new NotFoundException("Customers not found");
    }

    return new GetCustomerByFilterInteractorResponse(customers);
  }
}
