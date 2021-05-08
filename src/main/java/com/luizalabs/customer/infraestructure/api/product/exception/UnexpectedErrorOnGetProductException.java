package com.luizalabs.customer.infraestructure.api.product.exception;

import com.luizalabs.customer.domain.exception.BadGatewayException;

import java.util.UUID;

public class UnexpectedErrorOnGetProductException extends BadGatewayException {
  public UnexpectedErrorOnGetProductException(UUID id, Throwable t) {
    super("Unexpected error on get product " + id + ": " + t);
  }
}
