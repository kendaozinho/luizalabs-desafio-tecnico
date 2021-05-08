package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.NotFoundException;
import com.luizalabs.customer.domain.gateway.customer.GetAllCustomersGateway;
import com.luizalabs.customer.domain.interactor.customer.DeleteAllCustomersInteractor;
import com.luizalabs.customer.domain.interactor.customer.DeleteCustomerByIdInteractor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class DeleteAllCustomersInteractorImpl implements DeleteAllCustomersInteractor {
  GetAllCustomersGateway getAllCustomersGateway;
  DeleteCustomerByIdInteractor deleteCustomerByIdInteractor;

  public DeleteAllCustomersInteractorImpl(
      GetAllCustomersGateway getAllCustomersGateway,
      DeleteCustomerByIdInteractor deleteCustomerByIdInteractor
  ) {
    this.getAllCustomersGateway = getAllCustomersGateway;
    this.deleteCustomerByIdInteractor = deleteCustomerByIdInteractor;
  }

  @Override
  @Transactional
  public void execute() {
    ArrayList<Customer> customers;
    try {
      customers = this.getAllCustomersGateway.getAll();
    } catch (NotFoundException exception) {
      return;
    }

    customers.forEach(customer -> this.deleteCustomerByIdInteractor.execute(customer.getId()));
  }
}
