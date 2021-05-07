package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;

public interface GetCustomerByEmailGateway {
  CustomerTable findOneByEmail(String email);
}
