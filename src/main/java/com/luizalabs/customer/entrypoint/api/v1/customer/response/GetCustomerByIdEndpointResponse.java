package com.luizalabs.customer.entrypoint.api.v1.customer.response;

import com.luizalabs.customer.domain.entity.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

public class GetCustomerByIdEndpointResponse {
  private UUID id;
  private String name;
  private String email;
  private ArrayList<Product> products;

  public GetCustomerByIdEndpointResponse() {
  }

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

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public ArrayList<Product> getProducts() {
    return this.products;
  }

  public static class Product {
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

    public String getTitle() {
      return this.title;
    }

    public BigDecimal getPrice() {
      return this.price;
    }

    public String getImage() {
      return this.image;
    }
  }
}
