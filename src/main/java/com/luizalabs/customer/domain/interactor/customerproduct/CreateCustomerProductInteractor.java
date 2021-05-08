package com.luizalabs.customer.domain.interactor.customerproduct;

import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.exception.ConflictException;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

public interface CreateCustomerProductInteractor {
  CustomerProduct execute(CustomerProduct request) throws NotFoundException, ConflictException, InternalServerErrorException;
}
