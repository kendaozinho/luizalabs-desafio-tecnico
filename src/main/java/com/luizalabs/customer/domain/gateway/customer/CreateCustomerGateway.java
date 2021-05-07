package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;

public interface CreateCustomerGateway {
  CustomerTable create(CustomerTable request);
}
