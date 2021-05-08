package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

public interface DeleteAllCustomersInteractor {
  void execute() throws NotFoundException, InternalServerErrorException;
}
