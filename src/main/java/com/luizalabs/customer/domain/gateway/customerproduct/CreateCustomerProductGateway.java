package com.luizalabs.customer.domain.gateway.customerproduct;

import com.luizalabs.customer.infraestructure.database.customerproduct.table.CustomerProductTable;

public interface CreateCustomerProductGateway {
  CustomerProductTable create(CustomerProductTable request);
}
