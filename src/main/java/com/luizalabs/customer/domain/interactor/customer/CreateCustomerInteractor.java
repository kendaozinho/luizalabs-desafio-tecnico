package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.entity.Customer;

public interface CreateCustomerInteractor {
  Customer execute(Customer request);
}
