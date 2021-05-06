package com.luizalabs.customer.application.customerproduct.impl;

import com.luizalabs.customer.application.customerproduct.DeleteCustomerProductInteractor;
import com.luizalabs.customer.domain.database.customerproduct.CustomerProductDatabase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCustomerProductInteractorImpl implements DeleteCustomerProductInteractor {
  private CustomerProductDatabase database;

  public DeleteCustomerProductInteractorImpl(CustomerProductDatabase database) {
    this.database = database;
  }

  @Override
  public void execute(UUID customerId, UUID productId) {
    this.database.delete(customerId, productId);
  }
}
