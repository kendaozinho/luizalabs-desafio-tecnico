package com.luizalabs.customer.infraestructure.database.repository;

import com.luizalabs.customer.domain.database.table.CustomerProductTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerProductRepository extends JpaRepository<CustomerProductTable, CustomerProductTable.CustomerProductTableId> {
  CustomerProductTable findOneByCustomerIdAndProductId(UUID customerId, UUID productId);
}
