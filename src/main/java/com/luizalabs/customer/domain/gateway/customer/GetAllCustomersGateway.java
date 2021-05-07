package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;

import java.util.ArrayList;

public interface GetAllCustomersGateway {
  ArrayList<CustomerTable> findAll();
}
