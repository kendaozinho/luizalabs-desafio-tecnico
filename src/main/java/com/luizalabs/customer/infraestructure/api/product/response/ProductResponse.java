package com.luizalabs.customer.infraestructure.api.product.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
  private UUID id;
  private String title;
  private BigDecimal price;
  private String brand;
  private String image;
  private Integer reviewScore;
}
