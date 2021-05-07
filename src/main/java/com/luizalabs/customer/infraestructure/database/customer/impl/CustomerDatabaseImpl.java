package com.luizalabs.customer.infraestructure.database.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.gateway.customer.*;
import com.luizalabs.customer.infraestructure.database.customer.exception.CustomerEmailAlreadyExistsException;
import com.luizalabs.customer.infraestructure.database.customer.exception.CustomerNotFoundException;
import com.luizalabs.customer.infraestructure.database.customer.repository.CustomerRepository;
import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerDatabaseImpl implements
    GetCustomerByIdGateway,
    GetCustomerByEmailGateway,
    GetCustomersByNameGateway,
    GetAllCustomersGateway,
    CreateCustomerGateway,
    UpdateCustomerGateway,
    DeleteCustomerGateway {
  private CustomerRepository repository;

  public CustomerDatabaseImpl(CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public Customer getOneById(UUID id) {
    CustomerTable customer = this.repository.findOneById(id);

    if (customer == null) {
      throw new CustomerNotFoundException();
    }

    return customer.toEntity();
  }

  @Override
  public Customer getOneByEmail(String email) {
    CustomerTable customer = this.repository.findOneByEmail(email);

    if (customer == null) {
      throw new CustomerNotFoundException();
    }

    return customer.toEntity();
  }

  @Override
  public ArrayList<Customer> getAllByName(String name) {
    ArrayList<CustomerTable> tableCustomers = this.repository.findAllByNameContainingIgnoreCase(name);

    if (tableCustomers.isEmpty()) {
      throw new CustomerNotFoundException();
    }

    ArrayList<Customer> customers = new ArrayList<>();

    tableCustomers.forEach(tableCustomer -> customers.add(tableCustomer.toEntity()));

    return customers;
  }

  @Override
  public ArrayList<Customer> getAll() {
    List<CustomerTable> tableCustomers = this.repository.findAll();

    if (tableCustomers.isEmpty()) {
      throw new CustomerNotFoundException();
    }

    ArrayList<Customer> customers = new ArrayList<>();

    tableCustomers.forEach(tableCustomer -> customers.add(tableCustomer.toEntity()));

    return customers;
  }

  @Override
  public Customer create(Customer request) {
    CustomerTable existingCustomer = this.repository.findOneByEmail(request.getEmail());

    if (existingCustomer != null) {
      throw new CustomerEmailAlreadyExistsException();
    }

    CustomerTable newCustomer = this.repository.save(
        new CustomerTable(request.getName(), request.getEmail())
    );

    return newCustomer.toEntity();
  }

  @Override
  public Customer update(UUID id, Customer request) {
    CustomerTable customer = this.repository.findOneById(id);

    if (customer == null) {
      throw new CustomerNotFoundException();
    }

    CustomerTable customerTable = this.repository.findOneByEmail(request.getEmail());

    if (customerTable != null && !customerTable.getId().equals(id)) {
      throw new CustomerEmailAlreadyExistsException();
    }

    customer.setName(request.getName());
    customer.setEmail(request.getEmail());

    return this.repository.save(customer).toEntity();
  }

  @Override
  public void delete(UUID id) {
    CustomerTable customer = this.repository.findOneById(id);

    if (customer == null) {
      throw new CustomerNotFoundException();
    }

    this.repository.delete(customer);
  }
}
