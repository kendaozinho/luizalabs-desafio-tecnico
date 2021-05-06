package com.luizalabs.customer.domain.api.product;

import com.luizalabs.customer.domain.api.product.response.PagedProductResponse;
import com.luizalabs.customer.domain.api.product.response.ProductResponse;

import java.util.UUID;

public interface ProductApi {
  PagedProductResponse getProducts(Integer pageNumber);

  ProductResponse getProduct(UUID id);
}
