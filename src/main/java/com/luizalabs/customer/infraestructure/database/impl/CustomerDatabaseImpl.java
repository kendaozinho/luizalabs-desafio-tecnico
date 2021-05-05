package com.luizalabs.customer.infraestructure.database.impl;

import com.luizalabs.customer.domain.database.CustomerDatabase;
import com.luizalabs.customer.domain.database.table.CustomerTable;
import com.luizalabs.customer.infraestructure.database.repository.CustomerRepository;

import java.util.UUID;

public class CustomerDatabaseImpl implements CustomerDatabase {
  private CustomerRepository repository;

  public CustomerDatabaseImpl(CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public CustomerTable read(UUID id) {
    return null;
  }

  @Override
  public CustomerTable create(CustomerTable request) {
    return null;
  }

  @Override
  public CustomerTable update(CustomerTable request) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
