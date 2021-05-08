package com.luizalabs.customer.infraestructure.api.product.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luizalabs.customer.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagedProductApiResponse {
  private MetaApiResponse meta;
  private ArrayList<ProductApiResponse> products;

  public ArrayList<Product> toArrayListOfEntity() {
    ArrayList<Product> products = new ArrayList<>();
    this.products.forEach(product -> products.add(
        Product.builder()
            .id(product.getId())
            .title(product.getTitle())
            .price(product.getPrice())
            .image(product.getImage())
            .build()
    ));

    return products;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MetaApiResponse {
    @JsonProperty("page_number")
    private Long pageNumber;
    @JsonProperty("page_size")
    private Long pageSize;
  }
}
