package com.luizalabs.customer.infraestructure.database.customerproduct.impl;

import com.luizalabs.customer.domain.exception.ConflictException;
import com.luizalabs.customer.domain.exception.NotFoundException;
import com.luizalabs.customer.domain.gateway.customerproduct.CreateCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.DeleteCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductByIdGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductsByCustomerIdGateway;
import com.luizalabs.customer.infraestructure.database.customerproduct.repository.CustomerProductRepository;
import com.luizalabs.customer.infraestructure.database.customerproduct.table.CustomerProductTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CustomerProductDatabaseImpl implements
    GetCustomerProductByIdGateway,
    GetCustomerProductsByCustomerIdGateway,
    CreateCustomerProductGateway,
    DeleteCustomerProductGateway {
  private CustomerProductRepository repository;

  public CustomerProductDatabaseImpl(CustomerProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public CustomerProductTable findOne(UUID customerId, UUID productId) {
    CustomerProductTable customerProduct = this.repository.findOneByCustomerIdAndProductId(customerId, productId);

    if (customerProduct == null) {
      throw new NotFoundException("Customer Product not found");
    }

    return customerProduct;
  }

  @Override
  public ArrayList<CustomerProductTable> findAllByCustomerId(UUID customerId) {
    ArrayList<CustomerProductTable> customerProducts = this.repository.findAllByCustomerId(customerId);

    if (customerProducts == null) {
      throw new NotFoundException("Customer Products not found");
    }

    return customerProducts;
  }

  @Override
  public CustomerProductTable create(CustomerProductTable request) {
    CustomerProductTable customerProduct =
        this.repository.findOneByCustomerIdAndProductId(request.getCustomerId(), request.getProductId());

    if (customerProduct != null) {
      throw new ConflictException("Customer Product already exists");
    }

    return this.repository.save(request);
  }

  @Override
  public void delete(UUID customerId, UUID productId) {
    CustomerProductTable customerProduct = this.repository.findOneByCustomerIdAndProductId(customerId, productId);

    if (customerProduct == null) {
      throw new NotFoundException("Customer Product not found");
    }

    this.repository.delete(customerProduct);
  }
}
