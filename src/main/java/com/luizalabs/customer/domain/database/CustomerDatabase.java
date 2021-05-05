package com.luizalabs.customer.domain.database;

import com.luizalabs.customer.domain.database.table.CustomerTable;

import java.util.UUID;

public interface CustomerDatabase {
  CustomerTable read(UUID id);

  CustomerTable create(CustomerTable request);

  CustomerTable update(CustomerTable request);

  void delete(UUID id);
}
