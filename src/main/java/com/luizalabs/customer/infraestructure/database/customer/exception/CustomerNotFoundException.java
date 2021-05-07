package com.luizalabs.customer.infraestructure.database.customer.exception;

import com.luizalabs.customer.domain.exception.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {
  public CustomerNotFoundException() {
    super("Customer(s) not found");
  }
}
