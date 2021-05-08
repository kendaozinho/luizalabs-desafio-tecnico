package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public interface GetCustomerByIdInteractor {
  Customer execute(UUID id, Boolean showProducts) throws NotFoundException, InternalServerErrorException;
}
