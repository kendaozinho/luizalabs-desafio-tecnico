package com.luizalabs.customer.domain.dto;

import com.luizalabs.customer.domain.database.table.CustomerTable;

import java.util.UUID;

public class CustomerDto {
  private UUID id;
  private String name;
  private String email;

  public CustomerDto() {
  }

  public CustomerDto(UUID id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public CustomerTable toTable() {
    return new CustomerTable(this.id, this.name, this.email);
  }
}
