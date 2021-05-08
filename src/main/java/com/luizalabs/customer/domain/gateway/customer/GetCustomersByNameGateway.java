package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.ArrayList;

public interface GetCustomersByNameGateway {
  ArrayList<Customer> getAllByName(String name) throws NotFoundException, InternalServerErrorException;
}
