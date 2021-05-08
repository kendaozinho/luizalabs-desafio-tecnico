package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.ConflictException;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;

public interface CreateCustomerInteractor {
  Customer execute(Customer request) throws ConflictException, InternalServerErrorException;
}
