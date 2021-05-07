package com.luizalabs.customer.domain.interactor.customerproduct;

import com.luizalabs.customer.domain.entity.CustomerProduct;

public interface CreateCustomerProductInteractor {
  CustomerProduct execute(CustomerProduct request);
}
