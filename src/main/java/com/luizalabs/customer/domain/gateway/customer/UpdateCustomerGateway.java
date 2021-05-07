package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;

import java.util.UUID;

public interface UpdateCustomerGateway {
  CustomerTable update(UUID id, CustomerTable request);
}
