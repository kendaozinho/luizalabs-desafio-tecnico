package com.luizalabs.customer.infraestructure.api.product.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizalabs.customer.domain.entity.Product;
import com.luizalabs.customer.domain.gateway.product.GetProductByIdGateway;
import com.luizalabs.customer.infraestructure.api.product.client.ProductApiClient;
import com.luizalabs.customer.infraestructure.api.product.exception.ProductNotFoundException;
import com.luizalabs.customer.infraestructure.api.product.exception.UnableToGetProductException;
import com.luizalabs.customer.infraestructure.api.product.exception.UnexpectedErrorOnGetProductException;
import com.luizalabs.customer.infraestructure.api.product.response.ProductApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.Duration;
import java.util.UUID;

@Service
public class ProductApiGatewayImpl implements GetProductByIdGateway {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private ProductApiClient client;
  private RedisTemplate<String, String> redisTemplate;
  private ObjectMapper mapper;

  @Value("${spring.redis.enabled}")
  private Boolean redisEnabled;
  @Value("${spring.redis.product.timeout}")
  private Integer redisProductTimeout;

  public ProductApiGatewayImpl(ProductApiClient client, RedisTemplate<String, String> redisTemplate, ObjectMapper mapper) {
    this.client = client;
    this.redisTemplate = redisTemplate;
    this.mapper = mapper;
  }

  @Override
  public Product getOneById(UUID id) throws ProductNotFoundException, UnableToGetProductException, UnexpectedErrorOnGetProductException {
    try {
      String redisKey = "product-" + id;

      if (this.redisEnabled && this.redisTemplate.hasKey(redisKey)) {
        Product product = this.mapper.readValue(this.redisTemplate.opsForValue().get(redisKey), Product.class);
        this.logger.info("[PRODUCT REDIS] Product " + id + " was obtained with success!");
        return product;
      }

      HttpEntity<ProductApiResponse> response = this.client.getRestTemplate().getForEntity("/" + id + "/", ProductApiResponse.class);

      this.logger.info("[PRODUCT API] Product " + id + " was obtained with success!");

      Product product = response.getBody().toEntity();

      if (this.redisEnabled) {
        this.redisTemplate.opsForValue().set(
            redisKey, this.mapper.writeValueAsString(product), Duration.ofMillis(this.redisProductTimeout)
        );
      }

      return product;
    } catch (HttpStatusCodeException e) {
      String errorMessage = "[PRODUCT API] Unable to get product " + id + "\n" +
          "STATUS: " + e.getStatusCode().value() + " & BODY: " + e.getResponseBodyAsString();

      if (e.getStatusCode().is5xxServerError()) {
        this.logger.error(errorMessage);
      } else {
        this.logger.warn(errorMessage);
      }

      if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        throw new ProductNotFoundException(id);
      }

      throw new UnableToGetProductException(id);
    } catch (Throwable t) {
      this.logger.error("[PRODUCT API] Unable to get product " + id + ".\nTHROWABLE: " + t);
      throw new UnexpectedErrorOnGetProductException(id, t);
    }
  }
}
