package com.luizalabs.customer.domain.interactor.customerproduct.impl;

import com.luizalabs.customer.domain.gateway.customer.GetCustomerByIdGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.CreateCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.product.GetProductByIdGateway;
import com.luizalabs.customer.domain.interactor.customerproduct.CreateCustomerProductInteractor;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.request.CreateCustomerProductEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.CreateCustomerProductEndpointResponse;
import com.luizalabs.customer.infraestructure.api.product.response.ProductResponse;
import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;
import com.luizalabs.customer.infraestructure.database.customerproduct.table.CustomerProductTable;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerProductInteractorImpl implements CreateCustomerProductInteractor {
  private GetCustomerByIdGateway getCustomerByIdGateway;
  private GetProductByIdGateway getProductByIdGateway;
  private CreateCustomerProductGateway createCustomerProductGateway;

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
  public CreateCustomerProductEndpointResponse execute(CreateCustomerProductEndpointRequest request) {
    CustomerTable customer = this.getCustomerByIdGateway.findOneById(request.getCustomerId()); // Validate if customer exists

    // TODO: Validate if product exists on Redis

    ProductResponse product = this.getProductByIdGateway.getProduct(request.getProductId());

    // TODO: Save product on Redis

    CustomerProductTable customerProduct = this.createCustomerProductGateway.create(
        new CustomerProductTable(customer.getId(), product.getId())
    );

    return new CreateCustomerProductEndpointResponse(customerProduct.getCustomerId(), customerProduct.getProductId());
  }
}
