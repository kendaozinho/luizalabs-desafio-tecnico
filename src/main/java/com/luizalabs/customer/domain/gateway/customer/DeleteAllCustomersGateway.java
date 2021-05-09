package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;

public interface DeleteAllCustomersGateway {
  void deleteAll() throws InternalServerErrorException;
}
