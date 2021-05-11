package com.luizalabs.customer.infraestructure.database.customerproduct.impl;

import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.gateway.customerproduct.*;
import com.luizalabs.customer.infraestructure.database.customerproduct.exception.CustomerProductAlreadyExistsException;
import com.luizalabs.customer.infraestructure.database.customerproduct.exception.CustomerProductNotFoundException;
import com.luizalabs.customer.infraestructure.database.customerproduct.repository.CustomerProductRepository;
import com.luizalabs.customer.infraestructure.database.customerproduct.table.CustomerProductTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerProductDatabaseGatewayImpl implements
    GetCustomerProductByIdGateway,
    GetCustomerProductsByCustomerIdGateway,
    CreateCustomerProductGateway,
    DeleteCustomerProductByIdGateway,
    DeleteAllCustomerProductsGateway {
  private final CustomerProductRepository repository;

  public CustomerProductDatabaseGatewayImpl(CustomerProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public CustomerProduct getOneById(UUID customerId, UUID productId) throws CustomerProductNotFoundException {
    CustomerProductTable customerProduct = this.repository.findOneByCustomerIdAndProductId(customerId, productId);

    if (customerProduct == null) {
      throw new CustomerProductNotFoundException();
    }

    return customerProduct.toEntity();
  }

  @Override
  public ArrayList<CustomerProduct> getAllByCustomerId(UUID customerId) throws CustomerProductNotFoundException {
    ArrayList<CustomerProductTable> customerProducts = this.repository.findAllByCustomerId(customerId);

    if (customerProducts.isEmpty()) {
      throw new CustomerProductNotFoundException();
    }

    return customerProducts.stream().map(CustomerProductTable::toEntity).collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public CustomerProduct create(CustomerProduct request) throws CustomerProductAlreadyExistsException {
    CustomerProductTable customerProduct =
        this.repository.findOneByCustomerIdAndProductId(request.getCustomerId(), request.getProductId());

    if (customerProduct != null) {
      throw new CustomerProductAlreadyExistsException();
    }

    return this.repository.saveAndFlush(
        new CustomerProductTable(request.getCustomerId(), request.getProductId())
    ).toEntity();
  }

  @Override
  public void deleteOneById(UUID customerId, UUID productId) throws CustomerProductNotFoundException {
    CustomerProductTable customerProduct = this.repository.findOneByCustomerIdAndProductId(customerId, productId);

    if (customerProduct == null) {
      throw new CustomerProductNotFoundException();
    }

    this.repository.delete(customerProduct);
  }

  @Override
  public void deleteAll() {
    this.repository.deleteAll();
  }
}
