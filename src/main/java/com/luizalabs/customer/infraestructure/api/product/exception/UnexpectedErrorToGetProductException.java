package com.luizalabs.customer.infraestructure.api.product.exception;

import com.luizalabs.customer.domain.exception.BadGatewayException;

import java.util.UUID;

public class UnexpectedErrorToGetProductException extends BadGatewayException {
  public UnexpectedErrorToGetProductException(UUID id, Throwable t) {
    super("Unexpected error to get product " + id + ": " + t);
  }
}
