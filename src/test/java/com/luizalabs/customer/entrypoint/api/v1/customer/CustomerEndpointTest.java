package com.luizalabs.customer.entrypoint.api.v1.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.gateway.customer.CreateCustomerGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByEmailGateway;
import com.luizalabs.customer.domain.interactor.customer.DeleteAllCustomersInteractor;
import com.luizalabs.customer.entrypoint.api.base.BaseEndpointTest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByFilterEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByIdEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerEndpointTest extends BaseEndpointTest {
  private String path = "/v1/customers";
  private GetCustomerByEmailGateway getCustomerByEmailGateway;
  private CreateCustomerGateway createCustomerGateway;

  @Autowired
  public CustomerEndpointTest(
      GetCustomerByEmailGateway getCustomerByEmailGateway,
      CreateCustomerGateway createCustomerGateway
  ) {
    this.getCustomerByEmailGateway = getCustomerByEmailGateway;
    this.createCustomerGateway = createCustomerGateway;
  }

  @BeforeAll
  @AfterAll
  public static void deleteAllCustomers(
      @Autowired DeleteAllCustomersInteractor deleteAllCustomersInteractor
  ) {
    deleteAllCustomersInteractor.execute();
  }

  @Test
  @Order(1)
  public void getOneByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "/" + UUID.randomUUID(), "Customer(s) not found");
  }

  @Test
  @Order(2)
  public void getAllIsNotFound() throws Throwable {
    super.getIsNotFound(this.path, "Customer list is empty");
  }

  @Test
  @Order(3)
  public void getAllByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?id=" + UUID.randomUUID(), "Customer list is empty");
  }

  @Test
  @Order(4)
  public void getAllByEmailIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?email=abc@luizalabs.com", "Customer list is empty");
  }

  @Test
  @Order(5)
  public void getAllByNameIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?name=abc", "Customer list is empty");
  }

  @Test
  @Order(6)
  public void postIsCreated() throws Throwable {
    CreateCustomerEndpointRequest request = new CreateCustomerEndpointRequest(
        "Kenneth Gottschalk de Azevedo", "kenneth@luizalabs.com"
    );

    CreateCustomerEndpointResponse response =
        super.postIsCreated(this.path, request, CreateCustomerEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getId());
    Assertions.assertEquals(response.getName(), request.getName());
    Assertions.assertEquals(response.getEmail(), request.getEmail());
  }

  @Test
  @Order(7)
  public void postIsConflict() throws Throwable {
    CreateCustomerEndpointRequest request = new CreateCustomerEndpointRequest(
        "Kenneth Gottschalk de Azevedo 2", "kenneth@luizalabs.com"
    );

    super.postIsConflict(this.path, request, "Customer email already exists");
  }

  @Test
  @Order(8)
  public void putIsNotFound() throws Throwable {
    UpdateCustomerEndpointRequest request = new UpdateCustomerEndpointRequest(
        "kendao", "kendao@luizalabs.com"
    );

    super.putIsNotFound(this.path + "/" + UUID.randomUUID(), request, "Customer(s) not found");
  }

  @Test
  @Order(9)
  public void putWithChangesIsOk() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kenneth@luizalabs.com");

    UpdateCustomerEndpointRequest request = new UpdateCustomerEndpointRequest(
        "kendao", "kendao@luizalabs.com"
    );

    UpdateCustomerEndpointResponse response =
        super.putIsOk(this.path + "/" + customer.getId(), request, UpdateCustomerEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getId(), customer.getId());
    Assertions.assertEquals(response.getName(), request.getName());
    Assertions.assertEquals(response.getEmail(), request.getEmail());
  }

  @Test
  @Order(10)
  public void putWithoutChangesIsOk() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    UpdateCustomerEndpointRequest request = new UpdateCustomerEndpointRequest(
        customer.getName(), customer.getEmail()
    );

    UpdateCustomerEndpointResponse response =
        super.putIsOk(this.path + "/" + customer.getId(), request, UpdateCustomerEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getId(), customer.getId());
    Assertions.assertEquals(response.getName(), request.getName());
    Assertions.assertEquals(response.getEmail(), request.getEmail());
  }

  @Test
  @Order(11)
  public void putIsConflict() throws Throwable {
    Customer customer = this.createCustomerGateway.create(
        Customer.builder().name("Yuri Gottschalk de Azevedo").email("yuri@luizalabs.com").build()
    );

    UpdateCustomerEndpointRequest request = new UpdateCustomerEndpointRequest(
        "Yuri Gottschalk de Azevedo 2", "kendao@luizalabs.com"
    );

    super.putIsConflict(this.path + "/" + customer.getId(), request, "Customer email already exists");
  }

  @Test
  @Order(12)
  public void deleteIsNotFound() throws Throwable {
    super.deleteIsNotFound(this.path + "/" + UUID.randomUUID(), "Customer(s) not found");
  }

  @Test
  @Order(13)
  public void deleteIsNoContent() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("yuri@luizalabs.com");

    super.deleteIsNoContent(this.path + "/" + customer.getId());
  }

  @Test
  @Order(14)
  public void getOneByIdIsOk() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    GetCustomerByIdEndpointResponse response =
        super.getIsOk(this.path + "/" + customer.getId(), GetCustomerByIdEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getId(), customer.getId());
    Assertions.assertEquals(response.getName(), customer.getName());
    Assertions.assertEquals(response.getEmail(), customer.getEmail());
  }

  @Test
  @Order(15)
  public void getAllIsOk() throws Throwable {
    GetCustomerByFilterEndpointResponse response =
        super.getIsOk(this.path, GetCustomerByFilterEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getMeta());
    Assertions.assertEquals(response.getMeta().getSize(), 1);
    Assertions.assertNotNull(response.getCustomers());
    Assertions.assertEquals(response.getCustomers().size(), 1);
    Assertions.assertNotNull(response.getCustomers().get(0).getId());
    Assertions.assertEquals(response.getCustomers().get(0).getName(), "kendao");
    Assertions.assertEquals(response.getCustomers().get(0).getEmail(), "kendao@luizalabs.com");
  }

  @Test
  @Order(16)
  public void getAllByIdIsOk() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    GetCustomerByFilterEndpointResponse response =
        super.getIsOk(this.path + "?id=" + customer.getId(), GetCustomerByFilterEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getMeta());
    Assertions.assertEquals(response.getMeta().getSize(), 1);
    Assertions.assertNotNull(response.getCustomers());
    Assertions.assertEquals(response.getCustomers().size(), 1);
    Assertions.assertEquals(response.getCustomers().get(0).getId(), customer.getId());
    Assertions.assertEquals(response.getCustomers().get(0).getName(), customer.getName());
    Assertions.assertEquals(response.getCustomers().get(0).getEmail(), customer.getEmail());
  }

  @Test
  @Order(17)
  public void getAllByEmailIsOk() throws Throwable {
    GetCustomerByFilterEndpointResponse response =
        super.getIsOk(this.path + "?email=kendao@luizalabs.com", GetCustomerByFilterEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getMeta());
    Assertions.assertEquals(response.getMeta().getSize(), 1);
    Assertions.assertNotNull(response.getCustomers());
    Assertions.assertEquals(response.getCustomers().size(), 1);
    Assertions.assertNotNull(response.getCustomers().get(0).getId());
    Assertions.assertEquals(response.getCustomers().get(0).getName(), "kendao");
    Assertions.assertEquals(response.getCustomers().get(0).getEmail(), "kendao@luizalabs.com");
  }

  @Test
  @Order(18)
  public void getAllByNameIsOk() throws Throwable {
    GetCustomerByFilterEndpointResponse response =
        super.getIsOk(this.path + "?name=ken", GetCustomerByFilterEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getMeta());
    Assertions.assertEquals(response.getMeta().getSize(), 1);
    Assertions.assertNotNull(response.getCustomers());
    Assertions.assertEquals(response.getCustomers().size(), 1);
    Assertions.assertNotNull(response.getCustomers().get(0).getId());
    Assertions.assertEquals(response.getCustomers().get(0).getName(), "kendao");
    Assertions.assertEquals(response.getCustomers().get(0).getEmail(), "kendao@luizalabs.com");
  }
}
