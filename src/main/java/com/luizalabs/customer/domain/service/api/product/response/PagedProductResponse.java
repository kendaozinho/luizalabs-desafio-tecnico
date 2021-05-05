package com.luizalabs.customer.domain.service.api.product.response;

import java.util.ArrayList;

public class PagedProductResponse {
  private MetaResponse meta;
  private ArrayList<ProductResponse> products;

  public PagedProductResponse() {
  }

  public MetaResponse getMeta() {
    return this.meta;
  }

  public ArrayList<ProductResponse> getProducts() {
    return this.products;
  }
}
