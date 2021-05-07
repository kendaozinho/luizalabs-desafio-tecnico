package com.luizalabs.customer.infraestructure.api.product.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizalabs.customer.domain.entity.Product;
import com.luizalabs.customer.domain.gateway.product.GetProductByIdGateway;
import com.luizalabs.customer.domain.gateway.product.GetProductsByPageNumberGateway;
import com.luizalabs.customer.infraestructure.api.product.client.ProductApiClient;
import com.luizalabs.customer.infraestructure.api.product.exception.*;
import com.luizalabs.customer.infraestructure.api.product.response.PagedProductResponse;
import com.luizalabs.customer.infraestructure.api.product.response.ProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class ProductApiImpl implements GetProductByIdGateway, GetProductsByPageNumberGateway {
  @Value("${spring.redis.product.timeout}")
  private Integer redisProductTimeout;

  private ProductApiClient client;
  private RedisTemplate<String, String> redisTemplate;
  private ObjectMapper mapper;

  public ProductApiImpl(ProductApiClient client, RedisTemplate<String, String> redisTemplate, ObjectMapper mapper) {
    this.client = client;
    this.redisTemplate = redisTemplate;
    this.mapper = mapper;
  }

  @Override
  public Product getOneById(UUID id) {
    try {
      String redisKey = "product-" + id;

      if (this.redisTemplate.hasKey(redisKey)) {
        Product product = this.mapper.readValue(this.redisTemplate.opsForValue().get(redisKey), Product.class);
        System.out.println("[PRODUCT REDIS] Product " + id + " was obtained with success!");
        return product;
      }

      HttpEntity<ProductResponse> response = this.client.getClient().getForEntity("/" + id + "/", ProductResponse.class);

      System.out.println("[PRODUCT API] Product " + id + " was obtained with success!");

      Product product = response.getBody().toEntity();

      this.redisTemplate.opsForValue().set(
          redisKey, this.mapper.writeValueAsString(product), Duration.ofMillis(this.redisProductTimeout)
      );

      System.out.println("[PRODUCT REDIS] Product " + id + " was saved with success!");

      return product;
    } catch (HttpStatusCodeException e) {
      System.err.println(
          "[PRODUCT API] Unable to get product " + id + "\n" +
              "STATUS: " + e.getStatusCode().value() + " & BODY: " + e.getResponseBodyAsString()
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
          "[PRODUCT API] Unable to get products on page " + pageNumber + "\n" +
              "STATUS: " + e.getStatusCode().value() + " & BODY: " + e.getResponseBodyAsString()
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
