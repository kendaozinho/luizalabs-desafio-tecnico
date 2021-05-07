package com.luizalabs.customer.domain.gateway.customerproduct;

import com.luizalabs.customer.domain.entity.CustomerProduct;

public interface CreateCustomerProductGateway {
  CustomerProduct create(CustomerProduct request);
}
