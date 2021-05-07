package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByIdGateway;
import com.luizalabs.customer.domain.interactor.customer.GetCustomerByIdInteractor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCustomerByIdInteractorImpl implements GetCustomerByIdInteractor {
  private GetCustomerByIdGateway getCustomerByIdGateway;

  public GetCustomerByIdInteractorImpl(GetCustomerByIdGateway getCustomerByIdGateway) {
    this.getCustomerByIdGateway = getCustomerByIdGateway;
  }

  @Override
  public Customer execute(UUID id) {
    return this.getCustomerByIdGateway.getOneById(id);
  }
}
