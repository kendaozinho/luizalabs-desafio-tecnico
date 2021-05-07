package com.luizalabs.customer.infraestructure.database.customer.exception;

import com.luizalabs.customer.domain.exception.ConflictException;

public class CustomerEmailAlreadyExistsException extends ConflictException {
  public CustomerEmailAlreadyExistsException() {
    super("Customer email already exists");
  }
}
