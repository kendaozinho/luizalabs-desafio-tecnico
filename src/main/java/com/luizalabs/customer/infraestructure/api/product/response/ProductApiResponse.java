package com.luizalabs.customer.infraestructure.api.product.response;

import com.luizalabs.customer.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductApiResponse {
  private UUID id;
  private String title;
  private BigDecimal price;
  private String image;
  private String brand;
  private Integer reviewScore;

  public Product toEntity() {
    return Product.builder().id(this.id).title(this.title).price(this.price).image(this.image).build();
  }
}