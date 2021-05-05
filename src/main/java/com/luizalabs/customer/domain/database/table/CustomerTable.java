package com.luizalabs.customer.domain.database.table;

import com.luizalabs.customer.domain.dto.CustomerDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class CustomerTable implements Serializable {
  @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public CustomerTable() {
  }

  public CustomerTable(UUID id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public UUID getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @PrePersist
  private void setCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  private void setUpdatedAt() {
    this.updatedAt = LocalDateTime.now();
  }

  public CustomerDto toDto() {
    return new CustomerDto(this.id, this.name, this.email);
  }
}