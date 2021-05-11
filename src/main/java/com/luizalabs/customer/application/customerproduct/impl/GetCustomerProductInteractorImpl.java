package com.luizalabs.customer.application.customerproduct.impl;

import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductByIdGateway;
import com.luizalabs.customer.domain.interactor.customerproduct.GetCustomerProductInteractor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCustomerProductInteractorImpl implements GetCustomerProductInteractor {
  private final GetCustomerProductByIdGateway getCustomerProductByIdGateway;

  public GetCustomerProductInteractorImpl(GetCustomerProductByIdGateway getCustomerProductByIdGateway) {
    this.getCustomerProductByIdGateway = getCustomerProductByIdGateway;
  }

  @Override
  public CustomerProduct execute(UUID customerId, UUID productId) {
    return this.getCustomerProductByIdGateway.getOneById(customerId, productId);
  }
}
