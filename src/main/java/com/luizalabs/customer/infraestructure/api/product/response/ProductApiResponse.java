package com.luizalabs.customer.infraestructure.api.product.response;

import com.luizalabs.customer.domain.entity.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductApiResponse {
  private UUID id;
  private String title;
  private BigDecimal price;
  private String image;
  private String brand;
  private Integer reviewScore;

  public ProductApiResponse() {
  }

  public ProductApiResponse(UUID id, String title, BigDecimal price, String image, String brand, Integer reviewScore) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.image = image;
    this.brand = brand;
    this.reviewScore = reviewScore;
  }

  public UUID getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public String getImage() {
    return this.image;
  }

  public String getBrand() {
    return this.brand;
  }

  public Integer getReviewScore() {
    return this.reviewScore;
  }

  public Product toEntity() {
    return new Product(this.getId(), this.getTitle(), this.getPrice(), this.getImage());
  }
}
