package com.luizalabs.customer.domain.interactor.customerproduct;

import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public interface GetCustomerProductInteractor {
  CustomerProduct execute(UUID customerId, UUID productId) throws NotFoundException, InternalServerErrorException;
}
