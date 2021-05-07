package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.entity.Customer;

public interface GetCustomerByEmailGateway {
  Customer getOneByEmail(String email);
}
