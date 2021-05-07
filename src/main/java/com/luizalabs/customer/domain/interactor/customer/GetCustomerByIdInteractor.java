package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.entity.Customer;

import java.util.UUID;

public interface GetCustomerByIdInteractor {
  Customer execute(UUID id, Boolean showProducts);
}
