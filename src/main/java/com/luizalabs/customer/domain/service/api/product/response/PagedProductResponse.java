package com.luizalabs.customer.domain.service.api.product.response;

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
}
