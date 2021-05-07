package com.luizalabs.customer.domain.interactor.customer.impl;

import com.luizalabs.customer.domain.gateway.customer.GetCustomerByIdGateway;
import com.luizalabs.customer.domain.gateway.customer.UpdateCustomerGateway;
import com.luizalabs.customer.domain.interactor.customer.UpdateCustomerInteractor;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;
import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCustomerInteractorImpl implements UpdateCustomerInteractor {
  private GetCustomerByIdGateway getCustomerByIdGateway;
  private UpdateCustomerGateway updateCustomerGateway;

  public UpdateCustomerInteractorImpl(
      GetCustomerByIdGateway getCustomerByIdGateway,
      UpdateCustomerGateway updateCustomerGateway
  ) {
    this.getCustomerByIdGateway = getCustomerByIdGateway;
    this.updateCustomerGateway = updateCustomerGateway;
  }

  @Override
  public UpdateCustomerEndpointResponse execute(UUID id, UpdateCustomerEndpointRequest request) {
    CustomerTable customer = this.getCustomerByIdGateway.findOneById(id);

    customer.setName(request.getName());
    customer.setEmail(request.getEmail());

    CustomerTable updatedCustomer = this.updateCustomerGateway.update(id, customer);

    return new UpdateCustomerEndpointResponse(updatedCustomer.getId(), updatedCustomer.getName(), updatedCustomer.getEmail());
  }
}
