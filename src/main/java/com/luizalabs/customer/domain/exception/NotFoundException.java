package com.luizalabs.customer.domain.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String details) {
    super(details);
  }
}