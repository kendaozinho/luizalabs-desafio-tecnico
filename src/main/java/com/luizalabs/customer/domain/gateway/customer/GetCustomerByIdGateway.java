package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;

import java.util.UUID;

public interface GetCustomerByIdGateway {
  CustomerTable findOneById(UUID id);
}
