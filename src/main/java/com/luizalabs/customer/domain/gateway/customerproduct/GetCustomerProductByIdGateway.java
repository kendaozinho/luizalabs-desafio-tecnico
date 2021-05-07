package com.luizalabs.customer.domain.gateway.customerproduct;

import com.luizalabs.customer.infraestructure.database.customerproduct.table.CustomerProductTable;

import java.util.UUID;

public interface GetCustomerProductByIdGateway {
  CustomerProductTable findOne(UUID customerId, UUID productId);
}
