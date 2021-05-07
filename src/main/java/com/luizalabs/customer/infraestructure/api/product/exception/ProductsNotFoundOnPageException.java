package com.luizalabs.customer.infraestructure.api.product.exception;

import com.luizalabs.customer.domain.exception.NotFoundException;

public class ProductsNotFoundOnPageException extends NotFoundException {
  public ProductsNotFoundOnPageException(Integer pageNumber) {
    super("Products not found on page " + pageNumber);
  }
}
