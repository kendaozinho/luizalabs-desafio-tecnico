package com.luizalabs.customer.domain.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  private UUID id;
  private String title;
  private BigDecimal price;
  private String image;
}
