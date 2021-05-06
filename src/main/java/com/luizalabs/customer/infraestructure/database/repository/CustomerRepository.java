package com.luizalabs.customer.infraestructure.database.repository;

import com.luizalabs.customer.domain.database.table.CustomerTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerTable, UUID> {
  CustomerTable findOneById(UUID id);

  CustomerTable findOneByEmail(String email);

  ArrayList<CustomerTable> findAllByName(String name);
}
