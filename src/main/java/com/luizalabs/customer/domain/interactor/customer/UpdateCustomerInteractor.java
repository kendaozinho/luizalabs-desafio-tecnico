package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.entity.Customer;

import java.util.UUID;

public interface UpdateCustomerInteractor {
  Customer execute(UUID id, Customer request);
}
