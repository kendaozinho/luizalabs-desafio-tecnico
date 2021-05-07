package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.entity.Customer;

import java.util.UUID;

public interface GetCustomerByIdGateway {
  Customer getOneById(UUID id);
}
