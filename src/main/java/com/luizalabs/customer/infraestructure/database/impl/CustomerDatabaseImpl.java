package com.luizalabs.customer.infraestructure.database.impl;

import com.luizalabs.customer.domain.database.CustomerDatabase;
import com.luizalabs.customer.domain.database.entity.CustomerEntity;
import com.luizalabs.customer.infraestructure.database.repository.CustomerRepository;

import java.util.UUID;

public class CustomerDatabaseImpl implements CustomerDatabase {
  private CustomerRepository repository;

  public CustomerDatabaseImpl(CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public CustomerEntity read(UUID id) {
    return null;
  }

  @Override
  public CustomerEntity create(CustomerEntity request) {
    return null;
  }

  @Override
  public CustomerEntity update(CustomerEntity request) {
    return null;
  }

  @Override
  public void delete(UUID id) {

  }
}
