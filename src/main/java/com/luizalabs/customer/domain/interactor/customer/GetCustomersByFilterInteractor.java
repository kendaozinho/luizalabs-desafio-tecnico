package com.luizalabs.customer.domain.interactor.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;

import java.util.ArrayList;
import java.util.UUID;

public interface GetCustomersByFilterInteractor {
  ArrayList<Customer> execute(UUID id, String name, String email) throws NotFoundException, InternalServerErrorException;
}
