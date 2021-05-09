package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public interface DeleteCustomerByIdGateway {
  void deleteOneById(UUID id) throws NotFoundException, InternalServerErrorException;
}
