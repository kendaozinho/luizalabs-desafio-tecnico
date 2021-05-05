package com.luizalabs.customer.domain.service.api.product;

import com.luizalabs.customer.domain.service.api.product.response.PagedProductResponse;
import com.luizalabs.customer.domain.service.api.product.response.ProductResponse;

import java.util.UUID;

public interface ProductApi {
  PagedProductResponse getProducts(Integer pageNumber);

  ProductResponse getProduct(UUID id);
}
