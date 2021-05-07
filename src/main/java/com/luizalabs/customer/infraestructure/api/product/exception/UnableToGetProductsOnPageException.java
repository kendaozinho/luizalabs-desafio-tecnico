package com.luizalabs.customer.infraestructure.api.product.exception;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;

public class UnableToGetProductsOnPageException extends InternalServerErrorException {
  public UnableToGetProductsOnPageException(Integer pageNumber) {
    super("Unable to get products on page " + pageNumber);
  }
}
