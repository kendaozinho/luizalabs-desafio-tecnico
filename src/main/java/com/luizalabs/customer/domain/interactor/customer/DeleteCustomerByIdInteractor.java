package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public interface DeleteCustomerByIdInteractor {
  void execute(UUID id) throws NotFoundException, InternalServerErrorException;
}
