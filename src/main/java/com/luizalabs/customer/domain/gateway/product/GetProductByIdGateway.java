package com.luizalabs.customer.domain.gateway.product;

import com.luizalabs.customer.domain.entity.Product;

import java.util.UUID;

public interface GetProductByIdGateway {
  Product getOneById(UUID id);
}
