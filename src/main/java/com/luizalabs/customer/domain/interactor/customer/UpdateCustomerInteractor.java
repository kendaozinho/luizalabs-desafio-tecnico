package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.ConflictException;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public interface UpdateCustomerInteractor {
  Customer execute(UUID id, Customer request) throws NotFoundException, ConflictException, InternalServerErrorException;
}
