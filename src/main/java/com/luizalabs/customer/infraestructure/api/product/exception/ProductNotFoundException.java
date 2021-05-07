package com.luizalabs.customer.infraestructure.api.product.exception;

import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.UUID;

public class ProductNotFoundException extends NotFoundException {
  public ProductNotFoundException(UUID id) {
    super("Product " + id + " not found");
  }
}
