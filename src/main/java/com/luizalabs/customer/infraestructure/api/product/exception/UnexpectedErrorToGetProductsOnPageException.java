package com.luizalabs.customer.infraestructure.api.product.exception;

import com.luizalabs.customer.domain.exception.BadGatewayException;

public class UnexpectedErrorToGetProductsOnPageException extends BadGatewayException {
  public UnexpectedErrorToGetProductsOnPageException(Integer pageNumber, Throwable t) {
    super("Unexpected error to get products on page " + pageNumber + ": " + t);
  }
}
