package com.luizalabs.customer.domain.database;

import com.luizalabs.customer.domain.database.entity.CustomerEntity;

import java.util.UUID;

public interface CustomerDatabase {
  CustomerEntity read(UUID id);

  CustomerEntity create(CustomerEntity request);

  CustomerEntity update(CustomerEntity request);

  void delete(UUID id);
}
