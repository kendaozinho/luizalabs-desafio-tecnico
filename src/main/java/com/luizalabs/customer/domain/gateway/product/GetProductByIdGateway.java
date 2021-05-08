package com.luizalabs.customer.domain.gateway.product;

import com.luizalabs.customer.domain.entity.Product;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public interface GetProductByIdGateway {
  Product getOneById(UUID id) throws NotFoundException, InternalServerErrorException;
}
