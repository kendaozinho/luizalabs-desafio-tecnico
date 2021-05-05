package com.luizalabs.customer.infraestructure.service.api.product.impl;

import com.luizalabs.customer.domain.service.api.product.ProductApi;
import com.luizalabs.customer.domain.service.api.product.response.PagedProductResponse;
import com.luizalabs.customer.domain.service.api.product.response.ProductResponse;
import com.luizalabs.customer.infraestructure.service.api.product.client.ProductClient;

import java.util.UUID;

public class ProductApiImpl implements ProductApi {
  private ProductClient client;

  public ProductApiImpl(ProductClient client) {
    this.client = client;
  }

  @Override
  public PagedProductResponse getProducts(Integer pageNumber) {
    return null;
  }

  @Override
  public ProductResponse getProduct(UUID id) {
    return null;
  }
}
