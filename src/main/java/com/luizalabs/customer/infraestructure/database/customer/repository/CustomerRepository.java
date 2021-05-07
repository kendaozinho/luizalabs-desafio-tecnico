package com.luizalabs.customer.infraestructure.database.customer.repository;

import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerTable, UUID> {
  CustomerTable findOneById(UUID id);

  CustomerTable findOneByEmail(String email);

  ArrayList<CustomerTable> findAllByNameContainingIgnoreCase(String name);
}