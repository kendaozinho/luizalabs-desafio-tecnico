package com.luizalabs.customer.application.customerproduct.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.entity.Product;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByIdGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.CreateCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.product.GetProductByIdGateway;
import com.luizalabs.customer.domain.interactor.customerproduct.CreateCustomerProductInteractor;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerProductInteractorImpl implements CreateCustomerProductInteractor {
  private final GetCustomerByIdGateway getCustomerByIdGateway;
  private final GetProductByIdGateway getProductByIdGateway;
  private final CreateCustomerProductGateway createCustomerProductGateway;

  public CreateCustomerProductInteractorImpl(
      GetCustomerByIdGateway getCustomerByIdGateway,
      GetProductByIdGateway getProductByIdGateway,
      CreateCustomerProductGateway createCustomerProductGateway
  ) {
    this.getCustomerByIdGateway = getCustomerByIdGateway;
    this.getProductByIdGateway = getProductByIdGateway;
    this.createCustomerProductGateway = createCustomerProductGateway;
  }

  @Override
  public CustomerProduct execute(CustomerProduct request) {
    Customer customer = this.getCustomerByIdGateway.getOneById(request.getCustomerId()); // Validate if customer exists
    Product product = this.getProductByIdGateway.getOneById(request.getProductId()); // Validate if product exists

    return this.createCustomerProductGateway.create(
        new CustomerProduct(customer.getId(), product.getId())
    );
  }
}
