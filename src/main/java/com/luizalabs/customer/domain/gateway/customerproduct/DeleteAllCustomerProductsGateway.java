package com.luizalabs.customer.domain.gateway.customerproduct;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;

public interface DeleteAllCustomerProductsGateway {
  void deleteAll() throws InternalServerErrorException;
}
