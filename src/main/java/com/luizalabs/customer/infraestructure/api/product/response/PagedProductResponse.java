package com.luizalabs.customer.infraestructure.api.product.response;

import com.luizalabs.customer.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagedProductResponse {
  private MetaResponse meta;
  private ArrayList<ProductResponse> products;

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
}
