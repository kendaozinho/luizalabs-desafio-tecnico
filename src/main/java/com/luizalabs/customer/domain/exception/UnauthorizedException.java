package com.luizalabs.customer.domain.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String details) {
    super(details);
  }
}