package com.luizalabs.customer.entrypoint.api.v1.customer;

import com.luizalabs.customer.domain.interactor.customer.DeleteAllCustomersInteractor;
import com.luizalabs.customer.entrypoint.api.base.BaseApiTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerEndpointTest extends BaseApiTest {
  private String path = "/v1/customers";
  private DeleteAllCustomersInteractor deleteAllCustomersInteractor;

  @Autowired
  public CustomerEndpointTest(
      DeleteAllCustomersInteractor deleteAllCustomersInteractor
  ) {
    this.deleteAllCustomersInteractor = deleteAllCustomersInteractor;
  }

  @Test
  @Order(1)
  public void getAllIsNotFound() throws Throwable {
    // Delete all customers before tests
    this.deleteAllCustomersInteractor.execute();

    super.getIsNotFound(this.path, "Customer(s) not found");
  }

  @Test
  @Order(2)
  public void getByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "/" + UUID.randomUUID(), "Customer(s) not found");
  }
}
