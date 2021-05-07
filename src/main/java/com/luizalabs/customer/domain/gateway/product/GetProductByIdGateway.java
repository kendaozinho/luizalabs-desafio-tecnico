package com.luizalabs.customer.domain.gateway.product;

import com.luizalabs.customer.infraestructure.api.product.response.ProductResponse;

import java.util.UUID;

public interface GetProductByIdGateway {
  ProductResponse getProduct(UUID id);
}
