package com.luizalabs.customer.application.customer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerInteractorResponse {
  private UUID id;
  private String name;
  private String email;
}
