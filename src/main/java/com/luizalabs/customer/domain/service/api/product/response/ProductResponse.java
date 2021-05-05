package com.luizalabs.customer.domain.service.api.product.response;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductResponse {
  private UUID id;
  private String title;
  private BigDecimal price;
  private String brand;
  private String image;

  public ProductResponse() {
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getBrand() {
    return brand;
  }

  public String getImage() {
    return image;
  }
}
