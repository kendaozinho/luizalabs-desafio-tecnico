package com.luizalabs.customer.domain.gateway.customerproduct;

import com.luizalabs.customer.domain.entity.CustomerProduct;

import java.util.ArrayList;
import java.util.UUID;

public interface GetCustomerProductsByCustomerIdGateway {
  ArrayList<CustomerProduct> getAllByCustomerId(UUID customerId);
}
