package com.luizalabs.customer.infraestructure.api.product.exception;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;

import java.util.UUID;

public class UnexpectedErrorToGetProductException extends InternalServerErrorException {
  public UnexpectedErrorToGetProductException(UUID id, Throwable t) {
    super("Unexpected error to get product " + id + ": " + t);
  }
}
