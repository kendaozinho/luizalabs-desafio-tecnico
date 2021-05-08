package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.ConflictException;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;

public interface CreateCustomerGateway {
  Customer create(Customer request) throws ConflictException, InternalServerErrorException;
}
