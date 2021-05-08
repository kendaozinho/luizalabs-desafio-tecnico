package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

public interface GetCustomerByEmailGateway {
  Customer getOneByEmail(String email) throws NotFoundException, InternalServerErrorException;
}
