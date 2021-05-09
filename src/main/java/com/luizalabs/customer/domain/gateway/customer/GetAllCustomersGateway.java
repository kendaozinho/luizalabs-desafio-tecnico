package com.luizalabs.customer.domain.gateway.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.ArrayList;

public interface GetAllCustomersGateway {
  ArrayList<Customer> getAll(Integer pageNumber, Integer pageSize) throws NotFoundException, InternalServerErrorException;
}
