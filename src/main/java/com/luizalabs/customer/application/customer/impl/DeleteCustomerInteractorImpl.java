package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.application.customer.DeleteCustomerInteractor;
import com.luizalabs.customer.domain.database.CustomerDatabase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCustomerInteractorImpl implements DeleteCustomerInteractor {
  private CustomerDatabase database;

  public DeleteCustomerInteractorImpl(CustomerDatabase database) {
    this.database = database;
  }

  @Override
  public void execute(UUID id) {
    this.database.delete(id);
  }
}
