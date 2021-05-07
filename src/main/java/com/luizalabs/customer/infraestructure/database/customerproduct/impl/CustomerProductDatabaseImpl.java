package com.luizalabs.customer.infraestructure.database.customerproduct.impl;

import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.gateway.customerproduct.CreateCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.DeleteCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductByIdGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductsByCustomerIdGateway;
import com.luizalabs.customer.infraestructure.database.customerproduct.exception.CustomerProductAlreadyExistsException;
import com.luizalabs.customer.infraestructure.database.customerproduct.exception.CustomerProductNotFoundException;
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
  public CustomerProduct getOneById(UUID customerId, UUID productId) {
    CustomerProductTable customerProduct = this.repository.findOneByCustomerIdAndProductId(customerId, productId);

    if (customerProduct == null) {
      throw new CustomerProductNotFoundException();
    }

    return customerProduct.toEntity();
  }

  @Override
  public ArrayList<CustomerProduct> getAllByCustomerId(UUID customerId) {
    ArrayList<CustomerProductTable> customerProductsOfTable = this.repository.findAllByCustomerId(customerId);

    if (customerProductsOfTable == null) {
      throw new CustomerProductNotFoundException();
    }

    ArrayList<CustomerProduct> customerProducts = new ArrayList<>();

    customerProductsOfTable.forEach(tableCustomer -> customerProducts.add(tableCustomer.toEntity()));

    return customerProducts;
  }

  @Override
  public CustomerProduct create(CustomerProduct request) {
    CustomerProductTable customerProduct =
        this.repository.findOneByCustomerIdAndProductId(request.getCustomerId(), request.getProductId());

    if (customerProduct != null) {
      throw new CustomerProductAlreadyExistsException();
    }

    return this.repository.save(
        new CustomerProductTable(request.getCustomerId(), request.getProductId())
    ).toEntity();
  }

  @Override
  public void delete(UUID customerId, UUID productId) {
    CustomerProductTable customerProduct = this.repository.findOneByCustomerIdAndProductId(customerId, productId);

    if (customerProduct == null) {
      throw new CustomerProductNotFoundException();
    }

    this.repository.delete(customerProduct);
  }
}