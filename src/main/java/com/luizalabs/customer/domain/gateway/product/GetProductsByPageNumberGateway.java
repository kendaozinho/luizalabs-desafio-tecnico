package com.luizalabs.customer.domain.gateway.product;

import com.luizalabs.customer.domain.entity.Product;

import java.util.ArrayList;

public interface GetProductsByPageNumberGateway {
  ArrayList<Product> getAllByPageNumber(Integer pageNumber);
}
