package com.luizalabs.customer.application.customerproduct.impl;

import com.luizalabs.customer.application.customerproduct.GetCustomerProductInteractor;
import com.luizalabs.customer.application.customerproduct.response.GetCustomerProductInteractorResponse;
import com.luizalabs.customer.domain.database.customerproduct.CustomerProductDatabase;
import com.luizalabs.customer.domain.database.customerproduct.table.CustomerProductTable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCustomerProductInteractorImpl implements GetCustomerProductInteractor {
  private CustomerProductDatabase database;

  public GetCustomerProductInteractorImpl(CustomerProductDatabase database) {
    this.database = database;
  }

  @Override
  public GetCustomerProductInteractorResponse execute(UUID customerId, UUID productId) {
    CustomerProductTable customerProduct = this.database.findOne(customerId, productId);

    return new GetCustomerProductInteractorResponse(customerProduct.getCustomerId(), customerProduct.getProductId());
  }
}
