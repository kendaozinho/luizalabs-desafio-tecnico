package com.luizalabs.customer.application.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.gateway.customer.CreateCustomerGateway;
import com.luizalabs.customer.domain.interactor.customer.CreateCustomerInteractor;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerInteractorImpl implements CreateCustomerInteractor {
  private CreateCustomerGateway createCustomerGateway;

  public CreateCustomerInteractorImpl(CreateCustomerGateway createCustomerGateway) {
    this.createCustomerGateway = createCustomerGateway;
  }

  @Override
  public Customer execute(Customer request) {
    return this.createCustomerGateway.create(
        Customer.builder().name(request.getName()).email(request.getEmail()).build()
    );
  }
}
