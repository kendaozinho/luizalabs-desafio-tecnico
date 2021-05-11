package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.gateway.customer.GetAllCustomersGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByEmailGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByIdGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomersByNameGateway;
import com.luizalabs.customer.domain.interactor.customer.GetCustomersByFilterInteractor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
public class GetCustomersByFilterInteractorImpl implements GetCustomersByFilterInteractor {
  private final GetCustomerByIdGateway getCustomerByIdGateway;
  private final GetCustomerByEmailGateway getCustomerByEmailGateway;
  private final GetCustomersByNameGateway getCustomersByNameGateway;
  private final GetAllCustomersGateway getAllCustomersGateway;

  public GetCustomersByFilterInteractorImpl(
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
  public ArrayList<Customer> execute(UUID id, String name, String email, Integer pageNumber, Integer pageSize) {
    if (id != null) {
      return new ArrayList<>(
          Collections.singletonList(this.getCustomerByIdGateway.getOneById(id))
      );
    } else if (email != null) {
      return new ArrayList<>(
          Collections.singletonList(this.getCustomerByEmailGateway.getOneByEmail(email))
      );
    } else if (name != null) {
      return this.getCustomersByNameGateway.getAllByName(name, pageNumber, pageSize);
    } else {
      return this.getAllCustomersGateway.getAll(pageNumber, pageSize);
    }
  }
}
