package com.luizalabs.customer.entrypoint.api.v1.customer.response;

import com.luizalabs.customer.domain.entity.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class GetCustomerByIdEndpointResponse {
  private UUID id;
  private String name;
  private String email;
  private ArrayList<ProductEndpointResponse> products;

  public GetCustomerByIdEndpointResponse() {
  }

  public GetCustomerByIdEndpointResponse(Customer customer) {
    this.id = customer.getId();
    this.name = customer.getName();
    this.email = customer.getEmail();

    if (customer.getProducts() != null) {
      this.products = customer.getProducts().stream().map(customerProduct ->
          new ProductEndpointResponse(
              customerProduct.getId(),
              customerProduct.getTitle(),
              customerProduct.getPrice(),
              customerProduct.getImage()
          )
      ).collect(Collectors.toCollection(ArrayList::new));
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

  public ArrayList<ProductEndpointResponse> getProducts() {
    return this.products;
  }

  public static class ProductEndpointResponse {
    private UUID id;
    private String title;
    private BigDecimal price;
    private String image;

    public ProductEndpointResponse() {
    }

    public ProductEndpointResponse(UUID id, String title, BigDecimal price, String image) {
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
