package com.luizalabs.customer.domain.gateway.product;

import com.luizalabs.customer.infraestructure.api.product.response.PagedProductResponse;

public interface GetProductsByPageNumberGateway {
  PagedProductResponse getProducts(Integer pageNumber);
}
