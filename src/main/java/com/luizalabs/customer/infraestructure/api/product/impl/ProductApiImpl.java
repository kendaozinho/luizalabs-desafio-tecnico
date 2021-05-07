package com.luizalabs.customer.infraestructure.api.product.impl;

import com.luizalabs.customer.domain.entity.Product;
import com.luizalabs.customer.domain.gateway.product.GetProductByIdGateway;
import com.luizalabs.customer.domain.gateway.product.GetProductsByPageNumberGateway;
import com.luizalabs.customer.infraestructure.api.product.client.ProductApiClient;
import com.luizalabs.customer.infraestructure.api.product.exception.*;
import com.luizalabs.customer.infraestructure.api.product.response.PagedProductResponse;
import com.luizalabs.customer.infraestructure.api.product.response.ProductResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ProductApiImpl implements GetProductByIdGateway, GetProductsByPageNumberGateway {
  private ProductApiClient client;

  public ProductApiImpl(ProductApiClient client) {
    this.client = client;
  }

  @Override
  public Product getOneById(UUID id) {
    try {
      HttpEntity<ProductResponse> response = this.client.getClient().getForEntity("/" + id + "/", ProductResponse.class);

      System.out.println("[PRODUCT API] Product " + id + " was obtained with success!");

      return response.getBody().toEntity();
    } catch (HttpStatusCodeException e) {
      System.err.println(
          "[PRODUCT API] Unable to get product " + id + ".\n" +
              "STATUS: " + e.getStatusCode().value() + "\nBODY: " + e.getResponseBodyAsString()
      );

      if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        throw new ProductNotFoundException(id);
      }

      throw new UnableToGetProductException(id);
    } catch (Throwable t) {
      System.err.println("[PRODUCT API] Unable to get product " + id + ".\nTHROWABLE: " + t);
      throw new UnexpectedErrorToGetProductException(id, t);
    }
  }

  @Override
  public ArrayList<Product> getAllByPageNumber(Integer pageNumber) {
    try {
      HttpEntity<PagedProductResponse> response = this.client.getClient().getForEntity("/?page=" + pageNumber, PagedProductResponse.class);

      System.out.println("[PRODUCT API] Products obtained with success on page " + pageNumber + "!");

      return response.getBody().toArrayListOfEntity();
    } catch (HttpStatusCodeException e) {
      System.err.println(
          "[PRODUCT API] Unable to get products on page " + pageNumber + ".\n" +
              "STATUS: " + e.getStatusCode().value() + "\nBODY: " + e.getResponseBodyAsString()
      );

      if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        throw new ProductsNotFoundOnPageException(pageNumber);
      }

      throw new UnableToGetProductsOnPageException(pageNumber);
    } catch (Throwable t) {
      System.err.println("[PRODUCT API] Unable to get products on page " + pageNumber + ".\nTHROWABLE: " + t);
      throw new UnexpectedErrorToGetProductsOnPageException(pageNumber, t);
    }
  }
}
