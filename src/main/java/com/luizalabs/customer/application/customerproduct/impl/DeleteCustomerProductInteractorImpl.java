package com.luizalabs.customer.application.customerproduct.impl;

import com.luizalabs.customer.domain.gateway.customerproduct.DeleteCustomerProductByIdGateway;
import com.luizalabs.customer.domain.interactor.customerproduct.DeleteCustomerProductInteractor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCustomerProductInteractorImpl implements DeleteCustomerProductInteractor {
  private final DeleteCustomerProductByIdGateway deleteCustomerProductByIdGateway;

  public DeleteCustomerProductInteractorImpl(DeleteCustomerProductByIdGateway deleteCustomerProductByIdGateway) {
    this.deleteCustomerProductByIdGateway = deleteCustomerProductByIdGateway;
  }

  @Override
  public void execute(UUID customerId, UUID productId) {
    this.deleteCustomerProductByIdGateway.deleteOneById(customerId, productId);
  }
}
