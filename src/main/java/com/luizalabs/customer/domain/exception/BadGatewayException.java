package com.luizalabs.customer.domain.exception;

public class BadGatewayException extends RuntimeException {
  public BadGatewayException(String details) {
    super(details);
  }
}