package com.luizalabs.customer.domain.gateway.customerproduct;

import com.luizalabs.customer.infraestructure.database.customerproduct.table.CustomerProductTable;

import java.util.ArrayList;
import java.util.UUID;

public interface GetCustomerProductsByCustomerIdGateway {
  ArrayList<CustomerProductTable> findAllByCustomerId(UUID customerId);
}
