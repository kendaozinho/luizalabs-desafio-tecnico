package com.luizalabs.customer.domain.entity;


import java.util.ArrayList;
import java.util.UUID;

public class Customer {
  private UUID id;
  private String name;
  private String email;
  private ArrayList<Product> products;

  public Customer() {
  }

  public Customer(UUID id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ArrayList<Product> getProducts() {
    return this.products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }
}
