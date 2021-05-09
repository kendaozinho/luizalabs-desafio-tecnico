package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.gateway.customer.UpdateCustomerGateway;
import com.luizalabs.customer.domain.interactor.customer.UpdateCustomerInteractor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCustomerInteractorImpl implements UpdateCustomerInteractor {
  private UpdateCustomerGateway updateCustomerGateway;

  public UpdateCustomerInteractorImpl(
      UpdateCustomerGateway updateCustomerGateway
  ) {
    this.updateCustomerGateway = updateCustomerGateway;
  }

  @Override
  public Customer execute(UUID id, Customer request) {
    return this.updateCustomerGateway.update(id, request);
  }
}
