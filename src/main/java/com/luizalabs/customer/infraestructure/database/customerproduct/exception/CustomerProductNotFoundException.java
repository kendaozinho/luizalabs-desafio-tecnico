package com.luizalabs.customer.infraestructure.database.customerproduct.exception;

import com.luizalabs.customer.domain.exception.NotFoundException;

public class CustomerProductNotFoundException extends NotFoundException {
  public CustomerProductNotFoundException() {
    super("Customer Product(s) not found");
  }
}
