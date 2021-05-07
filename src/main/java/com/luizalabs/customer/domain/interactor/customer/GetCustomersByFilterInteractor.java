package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.entity.Customer;

import java.util.ArrayList;
import java.util.UUID;

public interface GetCustomersByFilterInteractor {
  ArrayList<Customer> execute(UUID id, String name, String email);
}
