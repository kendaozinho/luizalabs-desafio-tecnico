package com.luizalabs.customer.domain.interactor.customer.impl;

import com.luizalabs.customer.domain.exception.NotFoundException;
import com.luizalabs.customer.domain.gateway.customer.GetAllCustomersGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByEmailGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByIdGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomersByNameGateway;
import com.luizalabs.customer.domain.interactor.customer.GetCustomerByFilterInteractor;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByFilterEndpointResponse;
import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class GetCustomerByFilterInteractorImpl implements GetCustomerByFilterInteractor {
  private GetCustomerByIdGateway getCustomerByIdGateway;
  private GetCustomerByEmailGateway getCustomerByEmailGateway;
  private GetCustomersByNameGateway getCustomersByNameGateway;
  private GetAllCustomersGateway getAllCustomersGateway;

  public GetCustomerByFilterInteractorImpl(
      GetCustomerByIdGateway getCustomerByIdGateway,
      GetCustomerByEmailGateway getCustomerByEmailGateway,
      GetCustomersByNameGateway getCustomersByNameGateway,
      GetAllCustomersGateway getAllCustomersGateway
  ) {
    this.getCustomerByIdGateway = getCustomerByIdGateway;
    this.getCustomerByEmailGateway = getCustomerByEmailGateway;
    this.getCustomersByNameGateway = getCustomersByNameGateway;
    this.getAllCustomersGateway = getAllCustomersGateway;
  }

  @Override
  public GetCustomerByFilterEndpointResponse execute(UUID id, String name, String email) {
    ArrayList<CustomerTable> customers = new ArrayList<>();

    if (id != null) {
      customers.add(this.getCustomerByIdGateway.findOneById(id));
    } else if (email != null) {
      customers.add(this.getCustomerByEmailGateway.findOneByEmail(email));
    } else if (name != null) {
      customers.addAll(this.getCustomersByNameGateway.findAllByName(name));
    } else {
      customers.addAll(this.getAllCustomersGateway.findAll());
    }

    if (customers.isEmpty()) {
      throw new NotFoundException("Customers not found");
    }

    return new GetCustomerByFilterEndpointResponse(customers);
  }
}
