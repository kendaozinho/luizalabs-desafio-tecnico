package com.luizalabs.customer.application.customerproduct.impl;

import com.luizalabs.customer.application.customerproduct.CreateCustomerProductInteractor;
import com.luizalabs.customer.application.customerproduct.request.CreateCustomerProductInteractorRequest;
import com.luizalabs.customer.application.customerproduct.response.CreateCustomerProductInteractorResponse;
import com.luizalabs.customer.domain.database.customer.CustomerDatabase;
import com.luizalabs.customer.domain.database.customer.table.CustomerTable;
import com.luizalabs.customer.domain.database.customerproduct.CustomerProductDatabase;
import com.luizalabs.customer.domain.database.customerproduct.table.CustomerProductTable;
import com.luizalabs.customer.domain.service.api.product.ProductApi;
import com.luizalabs.customer.domain.service.api.product.response.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerProductInteractorImpl implements CreateCustomerProductInteractor {
  private ProductApi productApi;
  private CustomerDatabase customerDatabase;
  private CustomerProductDatabase customerProductDatabase;

  public CreateCustomerProductInteractorImpl(
      ProductApi productApi,
      CustomerProductDatabase customerProductDatabase,
      CustomerDatabase customerDatabase
  ) {
    this.productApi = productApi;
    this.customerProductDatabase = customerProductDatabase;
    this.customerDatabase = customerDatabase;
  }

  @Override
  public CreateCustomerProductInteractorResponse execute(CreateCustomerProductInteractorRequest request) {
    CustomerTable customer = this.customerDatabase.findOneById(request.getCustomerId()); // Validate if customer exists

    // TODO: Validate if product exists on Redis

    ProductResponse product = this.productApi.getProduct(request.getProductId());

    // TODO: Save product on Redis

    CustomerProductTable customerProduct = this.customerProductDatabase.create(
        new CustomerProductTable(customer.getId(), product.getId())
    );

    return new CreateCustomerProductInteractorResponse(customerProduct.getCustomerId(), customerProduct.getProductId());
  }
}
