package com.luizalabs.customer.domain.interactor.customer.impl;

import com.luizalabs.customer.domain.gateway.customer.GetCustomerByIdGateway;
import com.luizalabs.customer.domain.interactor.customer.GetCustomerByIdInteractor;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByIdEndpointResponse;
import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCustomerByIdInteractorImpl implements GetCustomerByIdInteractor {
  private GetCustomerByIdGateway getCustomerByIdGateway;

  public GetCustomerByIdInteractorImpl(GetCustomerByIdGateway getCustomerByIdGateway) {
    this.getCustomerByIdGateway = getCustomerByIdGateway;
  }

  @Override
  public GetCustomerByIdEndpointResponse execute(UUID id) {
    CustomerTable customer = this.getCustomerByIdGateway.findOneById(id);

    return new GetCustomerByIdEndpointResponse(customer.getId(), customer.getName(), customer.getEmail());
  }
}
