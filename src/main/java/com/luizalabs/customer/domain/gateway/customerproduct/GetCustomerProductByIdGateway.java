package com.luizalabs.customer.domain.gateway.customerproduct;

import com.luizalabs.customer.domain.entity.CustomerProduct;

import java.util.UUID;

public interface GetCustomerProductByIdGateway {
  CustomerProduct getOneById(UUID customerId, UUID productId);
}
