package com.luizalabs.customer.domain.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  private UUID id;
  private String name;
  private String email;
  private ArrayList<Product> products;
}
