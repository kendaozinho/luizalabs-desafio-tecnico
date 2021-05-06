package com.luizalabs.customer.domain.database.customer;

import com.luizalabs.customer.domain.database.customer.table.CustomerTable;

import java.util.ArrayList;
import java.util.UUID;

public interface CustomerDatabase {
  CustomerTable findOneById(UUID id);

  CustomerTable findOneByEmail(String email);

  ArrayList<CustomerTable> findAllByName(String name);

  ArrayList<CustomerTable> findAll();

  CustomerTable create(CustomerTable request);

  CustomerTable update(UUID id, CustomerTable request);

  void delete(UUID id);
}
