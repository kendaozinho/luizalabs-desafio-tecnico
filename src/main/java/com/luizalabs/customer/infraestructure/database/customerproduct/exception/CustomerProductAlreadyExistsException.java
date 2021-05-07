package com.luizalabs.customer.infraestructure.database.customerproduct.exception;

import com.luizalabs.customer.domain.exception.ConflictException;

public class CustomerProductAlreadyExistsException extends ConflictException {
  public CustomerProductAlreadyExistsException() {
    super("Customer Product already exists");
  }
}
