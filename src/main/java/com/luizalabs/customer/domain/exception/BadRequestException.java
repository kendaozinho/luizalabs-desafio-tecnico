package com.luizalabs.customer.domain.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String details) {
    super(details);
  }
}