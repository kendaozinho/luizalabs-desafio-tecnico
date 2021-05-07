package com.luizalabs.customer.infraestructure.api.product.impl;

import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;
import com.luizalabs.customer.domain.gateway.product.GetProductByIdGateway;
import com.luizalabs.customer.domain.gateway.product.GetProductsByPageNumberGateway;
import com.luizalabs.customer.infraestructure.api.product.client.ProductApiClient;
import com.luizalabs.customer.infraestructure.api.product.response.PagedProductResponse;
import com.luizalabs.customer.infraestructure.api.product.response.ProductResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.UUID;

@Service
public class ProductApiImpl implements GetProductByIdGateway, GetProductsByPageNumberGateway {
  private ProductApiClient client;

  public ProductApiImpl(ProductApiClient client) {
    this.client = client;
  }

  @Override
  public PagedProductResponse getProducts(Integer pageNumber) {
    try {
      HttpEntity<PagedProductResponse> response = this.client.getClient().getForEntity("/?page=" + pageNumber, PagedProductResponse.class);

      System.out.println("[PRODUCT API] Products obtained with success on page " + pageNumber + "!");

      return response.getBody();
    } catch (HttpStatusCodeException e) {
      System.err.println(
          "[PRODUCT API] Unable to get products on page " + pageNumber + ".\n" +
              "STATUS: " + e.getStatusCode().value() + "\nBODY: " + e.getResponseBodyAsString()
      );

      if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        throw new NotFoundException("Products not found on page " + pageNumber);
      }

      throw new InternalServerErrorException("Unable to get products on page " + pageNumber);
    } catch (Throwable t) {
      System.err.println("[PRODUCT API] Unable to get products on page " + pageNumber + ".\nTHROWABLE: " + t.toString());
      throw new InternalServerErrorException(t.toString());
    }
  }

  @Override
  public ProductResponse getProduct(UUID id) {
    try {
      HttpEntity<ProductResponse> response = this.client.getClient().getForEntity("/" + id + "/", ProductResponse.class);

      System.out.println("[PRODUCT API] Product " + id + " was obtained with success!");

      return response.getBody();
    } catch (HttpStatusCodeException e) {
      System.err.println(
          "[PRODUCT API] Unable to get product " + id + ".\n" +
              "STATUS: " + e.getStatusCode().value() + "\nBODY: " + e.getResponseBodyAsString()
      );

      if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        throw new NotFoundException("Product " + id + " not found");
      }

      throw new InternalServerErrorException("Unable to get product " + id);
    } catch (Throwable t) {
      System.err.println("[PRODUCT API] Unable to get product " + id + ".\nTHROWABLE: " + t.toString());
      throw new InternalServerErrorException(t.toString());
    }
  }
}
