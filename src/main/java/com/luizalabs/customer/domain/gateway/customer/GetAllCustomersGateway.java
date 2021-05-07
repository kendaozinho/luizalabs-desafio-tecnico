package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.entity.Customer;

import java.util.ArrayList;

public interface GetAllCustomersGateway {
  ArrayList<Customer> getAll();
}
