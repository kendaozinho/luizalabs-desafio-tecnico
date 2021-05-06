package com.luizalabs.customer.domain.database.customerproduct;

import com.luizalabs.customer.domain.database.customerproduct.table.CustomerProductTable;

import java.util.ArrayList;
import java.util.UUID;

public interface CustomerProductDatabase {
  CustomerProductTable findOne(UUID customerId, UUID productId);

  ArrayList<CustomerProductTable> findAllByCustomerId(UUID customerId);

  CustomerProductTable create(CustomerProductTable request);

  void delete(UUID customerId, UUID productId);
}
