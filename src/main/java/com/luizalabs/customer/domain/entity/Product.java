package com.luizalabs.customer.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
  private UUID id;
  private String title;
  private BigDecimal price;
  private String image;

  public Product() {
  }

  public Product(UUID id, String title, BigDecimal price, String image) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.image = image;
  }

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
