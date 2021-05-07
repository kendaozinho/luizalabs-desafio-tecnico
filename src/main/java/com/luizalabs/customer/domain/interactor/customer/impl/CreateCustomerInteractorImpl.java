package com.luizalabs.customer.domain.interactor.customer.impl;

import com.luizalabs.customer.domain.gateway.customer.CreateCustomerGateway;
import com.luizalabs.customer.domain.interactor.customer.CreateCustomerInteractor;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerInteractorImpl implements CreateCustomerInteractor {
  private CreateCustomerGateway createCustomerGateway;

  public CreateCustomerInteractorImpl(CreateCustomerGateway createCustomerGateway) {
    this.createCustomerGateway = createCustomerGateway;
  }

  @Override
  public CreateCustomerEndpointResponse execute(CreateCustomerEndpointRequest request) {
    CustomerTable newCustomer = this.createCustomerGateway.create(
        new CustomerTable(request.getName(), request.getEmail())
    );

    return new CreateCustomerEndpointResponse(newCustomer.getId(), newCustomer.getName(), newCustomer.getEmail());
  }
}
