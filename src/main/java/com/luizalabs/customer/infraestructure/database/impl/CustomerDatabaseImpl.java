package com.luizalabs.customer.infraestructure.database.impl;

import com.luizalabs.customer.domain.database.CustomerDatabase;
import com.luizalabs.customer.domain.database.table.CustomerTable;
import com.luizalabs.customer.infraestructure.database.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerDatabaseImpl implements CustomerDatabase {
  private CustomerRepository repository;

  public CustomerDatabaseImpl(CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public CustomerTable findOneById(UUID id) {
    CustomerTable customer = this.repository.findOneById(id);

    if (customer == null) {
      throw new RuntimeException("Customer not found");
    }

    return customer;
  }

  @Override
  public CustomerTable findOneByEmail(String email) {
    CustomerTable customer = this.repository.findOneByEmail(email);

    if (customer == null) {
      throw new RuntimeException("Customer not found");
    }

    return customer;
  }

  @Override
  public ArrayList<CustomerTable> findAllByName(String name) {
    ArrayList<CustomerTable> customers = this.repository.findAllByName(name);

    if (customers.isEmpty()) {
      throw new RuntimeException("Customers not found");
    }

    return customers;
  }

  @Override
  public ArrayList<CustomerTable> findAll() {
    List<CustomerTable> customers = this.repository.findAll();

    if (customers.isEmpty()) {
      throw new RuntimeException("Customers not found");
    }

    return new ArrayList<>(customers);
  }

  @Override
  public CustomerTable create(CustomerTable request) {
    CustomerTable existingCustomer = this.repository.findOneByEmail(request.getEmail());

    if (existingCustomer != null) {
      throw new RuntimeException("Customer already exists");
    }

    CustomerTable newCustomer = this.repository.save(
        new CustomerTable(request.getName(), request.getEmail())
    );

    return newCustomer;
  }

  @Override
  public CustomerTable update(UUID id, CustomerTable request) {
    CustomerTable customer = this.repository.findOneById(id);

    if (customer == null) {
      throw new RuntimeException("Customer not found");
    }

    CustomerTable customerTable = this.repository.findOneByEmail(request.getEmail());

    if (customerTable != null && !customerTable.getId().equals(id)) {
      throw new RuntimeException("Customer already exists with this email");
    }

    request.setId(id);
    return this.repository.save(request);
  }

  @Override
  public void delete(UUID id) {
    CustomerTable customer = this.repository.findOneById(id);

    if (customer == null) {
      throw new RuntimeException("Customer not found");
    }

    this.repository.delete(customer);
  }
}
