package com.luizalabs.customer.domain.gateway.customerproduct;

import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.ArrayList;
import java.util.UUID;

public interface GetCustomerProductsByCustomerIdGateway {
  ArrayList<CustomerProduct> getAllByCustomerId(UUID customerId) throws NotFoundException, InternalServerErrorException;
}
