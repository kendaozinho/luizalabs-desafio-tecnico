package com.luizalabs.customer.infraestructure.api.product.exception;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;

public class UnexpectedErrorToGetProductsOnPageException extends InternalServerErrorException {
  public UnexpectedErrorToGetProductsOnPageException(Integer pageNumber, Throwable t) {
    super("Unexpected error to get products on page " + pageNumber + ": " + t);
  }
}
