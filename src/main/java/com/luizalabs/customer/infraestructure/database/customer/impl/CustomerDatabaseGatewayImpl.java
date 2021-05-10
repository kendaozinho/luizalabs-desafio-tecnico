package com.luizalabs.customer.infraestructure.database.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.gateway.customer.*;
import com.luizalabs.customer.infraestructure.database.customer.exception.CustomerEmailAlreadyExistsException;
import com.luizalabs.customer.infraestructure.database.customer.exception.CustomerNotFoundException;
import com.luizalabs.customer.infraestructure.database.customer.repository.CustomerRepository;
import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerDatabaseGatewayImpl implements
    GetCustomerByIdGateway,
    GetCustomerByEmailGateway,
    GetCustomersByNameGateway,
    GetAllCustomersGateway,
    CreateCustomerGateway,
    UpdateCustomerGateway,
    DeleteCustomerByIdGateway,
    DeleteAllCustomersGateway {
  private CustomerRepository repository;

  public CustomerDatabaseGatewayImpl(CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public Customer getOneById(UUID id) throws CustomerNotFoundException {
    CustomerTable customer = this.repository.findOneById(id);

    if (customer == null) {
      throw new CustomerNotFoundException();
    }

    return customer.toEntity();
  }

  @Override
  public Customer getOneByEmail(String email) throws CustomerNotFoundException {
    CustomerTable customer = this.repository.findOneByEmail(email);

    if (customer == null) {
      throw new CustomerNotFoundException();
    }

    return customer.toEntity();
  }

  @Override
  public ArrayList<Customer> getAllByName(String name, Integer pageNumber, Integer pageSize) throws CustomerNotFoundException {
    Page<CustomerTable> customers =
        this.repository.findAllByNameContainingIgnoreCase(
            name, PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"))
        );

    if (customers.isEmpty()) {
      throw new CustomerNotFoundException();
    }

    return customers.stream().map(CustomerTable::toEntity).collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public ArrayList<Customer> getAll(Integer pageNumber, Integer pageSize) throws CustomerNotFoundException {
    Page<CustomerTable> customers = this.repository.findAll(
        PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "name"))
    );

    if (customers.isEmpty()) {
      throw new CustomerNotFoundException();
    }

    return customers.stream().map(CustomerTable::toEntity).collect(Collectors.toCollection(ArrayList::new));
  }

  @Override
  public Customer create(Customer request) throws CustomerEmailAlreadyExistsException {
    CustomerTable customer = this.repository.findOneByEmail(request.getEmail());

    if (customer != null) {
      throw new CustomerEmailAlreadyExistsException();
    }

    return this.repository.saveAndFlush(
        new CustomerTable(request.getName(), request.getEmail())
    ).toEntity();
  }

  @Override
  public Customer update(UUID id, Customer request) throws CustomerNotFoundException, CustomerEmailAlreadyExistsException {
    CustomerTable customer = this.repository.findOneById(id);

    if (customer == null) {
      throw new CustomerNotFoundException();
    }

    CustomerTable customerFilteredByEmail = this.repository.findOneByEmail(request.getEmail());

    if (customerFilteredByEmail != null && !customerFilteredByEmail.getId().equals(id)) {
      throw new CustomerEmailAlreadyExistsException();
    }

    customer.setName(request.getName());
    customer.setEmail(request.getEmail());

    return this.repository.saveAndFlush(customer).toEntity();
  }

  @Override
  public void deleteOneById(UUID id) throws CustomerNotFoundException {
    CustomerTable customer = this.repository.findOneById(id);

    if (customer == null) {
      throw new CustomerNotFoundException();
    }

    this.repository.delete(customer);
  }

  @Override
  public void deleteAll() {
    this.repository.deleteAll();
  }
}
