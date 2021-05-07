package com.luizalabs.customer.application.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.NotFoundException;
import com.luizalabs.customer.domain.gateway.customer.GetAllCustomersGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByEmailGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByIdGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomersByNameGateway;
import com.luizalabs.customer.domain.interactor.customer.GetCustomersByFilterInteractor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class GetCustomersByFilterInteractorImpl implements GetCustomersByFilterInteractor {
  private GetCustomerByIdGateway getCustomerByIdGateway;
  private GetCustomerByEmailGateway getCustomerByEmailGateway;
  private GetCustomersByNameGateway getCustomersByNameGateway;
  private GetAllCustomersGateway getAllCustomersGateway;

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
  public ArrayList<Customer> execute(UUID id, String name, String email) {
    ArrayList<Customer> customers = new ArrayList<>();

    if (id != null) {
      customers.add(this.getCustomerByIdGateway.getOneById(id));
    } else if (email != null) {
      customers.add(this.getCustomerByEmailGateway.getOneByEmail(email));
    } else if (name != null) {
      customers.addAll(this.getCustomersByNameGateway.getAllByName(name));
    } else {
      customers.addAll(this.getAllCustomersGateway.getAll());
    }

    if (customers.isEmpty()) {
      throw new NotFoundException("Customers not found");
    }

    return customers;
  }
}
