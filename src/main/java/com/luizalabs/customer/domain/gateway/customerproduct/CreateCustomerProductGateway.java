package com.luizalabs.customer.domain.gateway.customerproduct;

import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.exception.ConflictException;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;

public interface CreateCustomerProductGateway {
  CustomerProduct create(CustomerProduct request) throws ConflictException, InternalServerErrorException;
}
