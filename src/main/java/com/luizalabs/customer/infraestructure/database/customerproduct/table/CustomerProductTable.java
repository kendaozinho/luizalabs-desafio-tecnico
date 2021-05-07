package com.luizalabs.customer.infraestructure.database.customerproduct.table;

import com.luizalabs.customer.domain.entity.CustomerProduct;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customer_product")
@IdClass(CustomerProductTable.CustomerProductTableId.class)
public class CustomerProductTable implements Serializable {
  @Id
  @Column(name = "customer_id", nullable = false, updatable = false)
  private UUID customerId;

  @Id
  @Column(name = "product_id", nullable = false, updatable = false)
  private UUID productId;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public CustomerProductTable() {
  }

  public CustomerProductTable(UUID customerId, UUID productId) {
    this.customerId = customerId;
    this.productId = productId;
  }

  public UUID getCustomerId() {
    return this.customerId;
  }

  public UUID getProductId() {
    return this.productId;
  }

  @PrePersist
  private void setCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  private void setUpdatedAt() {
    this.updatedAt = LocalDateTime.now();
  }

  public CustomerProduct toEntity() {
    return new CustomerProduct(this.customerId, this.productId);
  }

  public static class CustomerProductTableId implements Serializable {
    private UUID customerId;
    private UUID productId;

    public CustomerProductTableId() {
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) return true;
      if (object == null || getClass() != object.getClass()) return false;
      CustomerProductTable customer = (CustomerProductTable) object;
      return (this.customerId.equals(customer.customerId) && this.productId.equals(customer.productId));
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.customerId, this.productId);
    }
  }
}