package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.application.customer.DeleteCustomerInteractor;
import com.luizalabs.customer.domain.database.customer.CustomerDatabase;
import com.luizalabs.customer.domain.database.customerproduct.CustomerProductDatabase;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class DeleteCustomerInteractorImpl implements DeleteCustomerInteractor {
  private CustomerDatabase customerDatabase;
  private CustomerProductDatabase customerProductDatabase;

  public DeleteCustomerInteractorImpl(
      CustomerDatabase customerDatabase,
      CustomerProductDatabase customerProductDatabase
  ) {
    this.customerDatabase = customerDatabase;
    this.customerProductDatabase = customerProductDatabase;
  }

  @Override
  @Transactional
  public void execute(UUID id) {
    this.customerProductDatabase.findAllByCustomerId(id).forEach(
        customerProduct -> this.customerProductDatabase.delete(customerProduct.getCustomerId(), customerProduct.getProductId())
    );

    this.customerDatabase.delete(id);
  }
}
