package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public interface GetCustomerByIdGateway {
  Customer getOneById(UUID id) throws NotFoundException, InternalServerErrorException;
}
