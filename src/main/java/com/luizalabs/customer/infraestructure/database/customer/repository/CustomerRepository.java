package com.luizalabs.customer.infraestructure.database.customer.repository;

import com.luizalabs.customer.infraestructure.database.customer.table.CustomerTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerTable, UUID> {
  CustomerTable findOneById(UUID id);

  CustomerTable findOneByEmail(String email);

  Page<CustomerTable> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

  Page<CustomerTable> findAll(Pageable pageable);
}
