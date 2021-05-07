package com.luizalabs.customer.application.customerproduct.impl;

import com.luizalabs.customer.domain.gateway.customerproduct.DeleteCustomerProductGateway;
import com.luizalabs.customer.domain.interactor.customerproduct.DeleteCustomerProductInteractor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCustomerProductInteractorImpl implements DeleteCustomerProductInteractor {
  private DeleteCustomerProductGateway deleteCustomerProductGateway;

  public DeleteCustomerProductInteractorImpl(DeleteCustomerProductGateway deleteCustomerProductGateway) {
    this.deleteCustomerProductGateway = deleteCustomerProductGateway;
  }

  @Override
  public void execute(UUID customerId, UUID productId) {
    this.deleteCustomerProductGateway.delete(customerId, productId);
  }
}
