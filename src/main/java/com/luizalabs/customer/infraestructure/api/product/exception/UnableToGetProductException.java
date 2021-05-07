package com.luizalabs.customer.infraestructure.api.product.exception;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;

import java.util.UUID;

public class UnableToGetProductException extends InternalServerErrorException {
  public UnableToGetProductException(UUID id) {
    super("Unable to get product " + id);
  }
}
