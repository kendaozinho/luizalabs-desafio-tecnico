package com.luizalabs.customer.domain.interactor.customerproduct;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public interface DeleteCustomerProductInteractor {
  void execute(UUID customerId, UUID productId) throws NotFoundException, InternalServerErrorException;
}
