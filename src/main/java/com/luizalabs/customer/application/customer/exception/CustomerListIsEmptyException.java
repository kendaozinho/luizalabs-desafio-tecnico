package com.luizalabs.customer.application.customer.exception;

import com.luizalabs.customer.domain.exception.NotFoundException;

public class CustomerListIsEmptyException extends NotFoundException {
  public CustomerListIsEmptyException() {
    super("Customer list is empty");
  }
}
