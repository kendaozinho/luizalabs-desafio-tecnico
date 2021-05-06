package com.luizalabs.customer.application.customerproduct.impl;

import com.luizalabs.customer.application.customerproduct.CreateCustomerProductInteractor;
import com.luizalabs.customer.application.customerproduct.request.CreateCustomerProductInteractorRequest;
import com.luizalabs.customer.application.customerproduct.response.CreateCustomerProductInteractorResponse;
import com.luizalabs.customer.domain.database.customer.CustomerDatabase;
import com.luizalabs.customer.domain.database.customer.table.CustomerTable;
import com.luizalabs.customer.domain.database.customerproduct.CustomerProductDatabase;
import com.luizalabs.customer.domain.database.customerproduct.table.CustomerProductTable;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerProductInteractorImpl implements CreateCustomerProductInteractor {
  private CustomerDatabase customerDatabase;
  private CustomerProductDatabase customerProductDatabase;

  public CreateCustomerProductInteractorImpl(
      CustomerProductDatabase customerProductDatabase,
      CustomerDatabase customerDatabase
  ) {
    this.customerProductDatabase = customerProductDatabase;
    this.customerDatabase = customerDatabase;
  }

  @Override
  public CreateCustomerProductInteractorResponse execute(CreateCustomerProductInteractorRequest request) {
    this.customerDatabase.findOneById(request.getCustomerId()); // Validate if customer exists

    CustomerProductTable customerProduct = this.customerProductDatabase.create(
        new CustomerProductTable(
            request.getCustomerId(), request.getProductId()
        )
    );

    return new CreateCustomerProductInteractorResponse(customerProduct.getCustomerId(), customerProduct.getProductId());
  }
}
