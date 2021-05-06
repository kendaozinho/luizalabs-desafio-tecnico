package com.luizalabs.customer.domain.exception;

public class ConflictException extends RuntimeException {
  public ConflictException(String details) {
    super(details);
  }
}