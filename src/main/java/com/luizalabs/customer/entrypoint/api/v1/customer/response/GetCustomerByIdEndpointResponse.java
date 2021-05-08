package com.luizalabs.customer.entrypoint.api.v1.customer.response;

import com.luizalabs.customer.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerByIdEndpointResponse {
  private UUID id;
  private String name;
  private String email;
  private ArrayList<Product> products;

  public GetCustomerByIdEndpointResponse(Customer customer) {
    this.id = customer.getId();
    this.name = customer.getName();
    this.email = customer.getEmail();

    if (customer.getProducts() != null) {
      this.products = new ArrayList<>();
      customer.getProducts().forEach(customerProduct -> this.products.add(
          new Product(
              customerProduct.getId(),
              customerProduct.getTitle(),
              customerProduct.getPrice(),
              customerProduct.getImage()
          )
      ));
    }
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Product {
    private UUID id;
    private String title;
    private BigDecimal price;
    private String image;
  }
}
