package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public interface DeleteCustomerGateway {
  void delete(UUID id) throws NotFoundException, InternalServerErrorException;
}
