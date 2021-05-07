package com.luizalabs.customer.domain.interactor.customerproduct.impl;

import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductByIdGateway;
import com.luizalabs.customer.domain.interactor.customerproduct.GetCustomerProductInteractor;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.GetCustomerProductEndpointResponse;
import com.luizalabs.customer.infraestructure.database.customerproduct.table.CustomerProductTable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCustomerProductInteractorImpl implements GetCustomerProductInteractor {
  private GetCustomerProductByIdGateway getCustomerProductByIdGateway;

  public GetCustomerProductInteractorImpl(GetCustomerProductByIdGateway getCustomerProductByIdGateway) {
    this.getCustomerProductByIdGateway = getCustomerProductByIdGateway;
  }

  @Override
  public GetCustomerProductEndpointResponse execute(UUID customerId, UUID productId) {
    CustomerProductTable customerProduct = this.getCustomerProductByIdGateway.findOne(customerId, productId);

    return new GetCustomerProductEndpointResponse(customerProduct.getCustomerId(), customerProduct.getProductId());
  }
}
