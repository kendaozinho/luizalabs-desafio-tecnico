package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.entity.Customer;

public interface CreateCustomerGateway {
  Customer create(Customer request);
}
