package com.luizalabs.customer.entrypoint.api.v1.customer;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.gateway.customer.CreateCustomerGateway;
import com.luizalabs.customer.domain.gateway.customer.DeleteAllCustomersGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByEmailGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.CreateCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.DeleteAllCustomerProductsGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductsByCustomerIdGateway;
import com.luizalabs.customer.entrypoint.api.base.BaseEndpointTest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByFilterEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByIdEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;
import com.luizalabs.customer.infraestructure.api.product.response.ProductApiResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerEndpointTest extends BaseEndpointTest {
  private String path = "/v1/customers";
  @Autowired
  private GetCustomerByEmailGateway getCustomerByEmailGateway;
  @Autowired
  private CreateCustomerGateway createCustomerGateway;
  @Autowired
  private CreateCustomerProductGateway createCustomerProductGateway;
  @Autowired
  private GetCustomerProductsByCustomerIdGateway getCustomerProductsByCustomerIdGateway;
  @Autowired
  private RestTemplateBuilder restTemplateBuilder; // build method returns a mock

  @BeforeAll
  @AfterAll
  public static void deleteAllCustomers(
      @Autowired DeleteAllCustomerProductsGateway deleteAllCustomerProductsGateway,
      @Autowired DeleteAllCustomersGateway deleteAllCustomersGateway
  ) {
    deleteAllCustomerProductsGateway.deleteAll();
    deleteAllCustomersGateway.deleteAll();
  }

  @Test
  @Order(1)
  public void getOneByIdIsBadRequest() throws Throwable {
    super.getIsBadRequest(this.path + "/abc", "Failed to convert value of type 'java.lang.String' to required type 'java.util.UUID'; nested exception is java.lang.IllegalArgumentException: Invalid UUID string: abc");
  }

  @Test
  @Order(2)
  public void getOneByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "/" + UUID.randomUUID(), "Customer(s) not found");
  }

  @Test
  @Order(3)
  public void getAllIsNotFound() throws Throwable {
    super.getIsNotFound(this.path, "Customer list is empty");
  }

  @Test
  @Order(4)
  public void getAllByIdIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?id=" + UUID.randomUUID(), "Customer list is empty");
  }

  @Test
  @Order(5)
  public void getAllByEmailIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?email=abc@luizalabs.com", "Customer list is empty");
  }

  @Test
  @Order(6)
  public void getAllByNameIsNotFound() throws Throwable {
    super.getIsNotFound(this.path + "?name=abc", "Customer list is empty");
  }

  @Test
  @Order(7)
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
  @Order(8)
  public void postIsConflict() throws Throwable {
    CreateCustomerEndpointRequest request = new CreateCustomerEndpointRequest(
        "Kenneth Gottschalk de Azevedo 2", "kenneth@luizalabs.com"
    );

    super.postIsConflict(this.path, request, "Customer email already exists");
  }

  @Test
  @Order(9)
  public void putIsNotFound() throws Throwable {
    UpdateCustomerEndpointRequest request = new UpdateCustomerEndpointRequest(
        "kendao", "kendao@luizalabs.com"
    );

    super.putIsNotFound(this.path + "/" + UUID.randomUUID(), request, "Customer(s) not found");
  }

  @Test
  @Order(10)
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
  @Order(11)
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
  @Order(12)
  public void putIsConflict() throws Throwable {
    Customer newCustomer = new Customer();

    newCustomer.setId(null);
    newCustomer.setName("Yuri Gottschalk de Azevedo");
    newCustomer.setEmail("yuri@luizalabs.com");

    Customer createdCustomer = this.createCustomerGateway.create(newCustomer);

    UpdateCustomerEndpointRequest request = new UpdateCustomerEndpointRequest(
        "Yuri Gottschalk de Azevedo 2", "kendao@luizalabs.com"
    );

    super.putIsConflict(this.path + "/" + createdCustomer.getId(), request, "Customer email already exists");
  }

  @Test
  @Order(13)
  public void deleteIsNotFound() throws Throwable {
    super.deleteIsNotFound(this.path + "/" + UUID.randomUUID(), "Customer(s) not found");
  }

  @Test
  @Order(14)
  public void deleteIsNoContentWithoutProducts() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("yuri@luizalabs.com");

    super.deleteIsNoContent(this.path + "/" + customer.getId());
  }

  @Test
  @Order(15)
  public void getOneByIdIsOkWhenExpandIsFalse() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    GetCustomerByIdEndpointResponse response =
        super.getIsOk(this.path + "/" + customer.getId() + "?expand=false", GetCustomerByIdEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getId(), customer.getId());
    Assertions.assertEquals(response.getName(), customer.getName());
    Assertions.assertEquals(response.getEmail(), customer.getEmail());
    Assertions.assertNull(response.getProducts());
  }

  @Test
  @Order(16)
  public void getOneByIdIsOkWithoutProducts() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    GetCustomerByIdEndpointResponse response =
        super.getIsOk(this.path + "/" + customer.getId(), GetCustomerByIdEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getId(), customer.getId());
    Assertions.assertEquals(response.getName(), customer.getName());
    Assertions.assertEquals(response.getEmail(), customer.getEmail());
    Assertions.assertNotNull(response.getProducts());
    Assertions.assertTrue(response.getProducts().isEmpty());
  }

  @Test
  @Order(17)
  public void getOneByIdIsOkWithProducts() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    UUID firstProductId = UUID.randomUUID();
    UUID secondProductId = UUID.randomUUID();

    RestTemplate restTemplate = this.restTemplateBuilder.build();

    Mockito.doReturn(
        new ResponseEntity<>(
            new ProductApiResponse(
                firstProductId,
                "Notebook",
                new BigDecimal("3199.99"),
                "http://www.images.com.br/" + firstProductId + ".jpg",
                "Dell",
                5
            ),
            HttpStatus.OK
        )
    ).when(restTemplate).getForEntity("/" + firstProductId + "/", ProductApiResponse.class);

    this.createCustomerProductGateway.create(new CustomerProduct(customer.getId(), firstProductId));

    Mockito.doReturn(
        new ResponseEntity<>(
            new ProductApiResponse(
                secondProductId,
                "Samsung Galaxy S10",
                new BigDecimal("2350.00"),
                "http://www.images.com.br/" + secondProductId + ".jpg",
                "Samsung",
                null
            ),
            HttpStatus.OK
        )
    ).when(restTemplate).getForEntity("/" + secondProductId + "/", ProductApiResponse.class);

    this.createCustomerProductGateway.create(new CustomerProduct(customer.getId(), secondProductId));

    // This product is no longer available
    Mockito.doThrow(
        new HttpClientErrorException(
            HttpStatus.NOT_FOUND,
            "Not Found",
            ("{\"error\": \"Product " + secondProductId + " not found\"}").getBytes(),
            Charset.defaultCharset()
        )
    ).when(restTemplate).getForEntity("/" + secondProductId + "/", ProductApiResponse.class);

    GetCustomerByIdEndpointResponse response =
        super.getIsOk(this.path + "/" + customer.getId(), GetCustomerByIdEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getId(), customer.getId());
    Assertions.assertEquals(response.getName(), customer.getName());
    Assertions.assertEquals(response.getEmail(), customer.getEmail());
    Assertions.assertNotNull(response.getProducts());
    Assertions.assertEquals(response.getProducts().size(), 1);
    Assertions.assertEquals(response.getProducts().get(0).getId(), firstProductId);
    Assertions.assertEquals(response.getProducts().get(0).getTitle(), "Notebook");
    Assertions.assertEquals(response.getProducts().get(0).getPrice(), new BigDecimal("3199.99"));
    Assertions.assertEquals(response.getProducts().get(0).getImage(), "http://www.images.com.br/" + firstProductId + ".jpg");
  }

  @Test
  @Order(18)
  public void getOneByIdIsInternalServerError() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    UUID productId = UUID.randomUUID();

    RestTemplate restTemplate = this.restTemplateBuilder.build();

    Mockito.doReturn(
        new ResponseEntity<>(
            new ProductApiResponse(
                productId,
                "Samsung Galaxy S20",
                new BigDecimal("3100.00"),
                "http://www.images.com.br/" + productId + ".jpg",
                "Samsung",
                null
            ),
            HttpStatus.OK
        )
    ).when(restTemplate).getForEntity("/" + productId + "/", ProductApiResponse.class);

    this.createCustomerProductGateway.create(new CustomerProduct(customer.getId(), productId));

    Mockito.doThrow(
        new HttpServerErrorException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error",
            ("{\"error\": \"Unable to get product " + productId + "\"}").getBytes(),
            Charset.defaultCharset()
        )
    ).when(restTemplate).getForEntity("/" + productId + "/", ProductApiResponse.class);

    // Redis returns OK for other products

    super.getIsInternalServerError(
        this.path + "/" + customer.getId(), "Unable to get product " + productId
    );
  }

  @Test
  @Order(19)
  public void getOneByIdIsBadGateway() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    ArrayList<CustomerProduct> customerProducts = this.getCustomerProductsByCustomerIdGateway.getAllByCustomerId(customer.getId());

    RestTemplate restTemplate = this.restTemplateBuilder.build();

    Mockito.doThrow(
        new RuntimeException("Unable to connect to server")
    ).when(restTemplate).getForEntity("/" + customerProducts.get(customerProducts.size() - 1).getProductId() + "/", ProductApiResponse.class);

    // Redis returns OK for other products

    super.getIsBadGateway(
        this.path + "/" + customer.getId(),
        "Unexpected error on get product " + customerProducts.get(customerProducts.size() - 1).getProductId() + ": java.lang.RuntimeException: Unable to connect to server"
    );
  }

  @Test
  @Order(20)
  public void getAllIsOk() throws Throwable {
    GetCustomerByFilterEndpointResponse response =
        super.getIsOk(this.path + "?offset=1&limit=1", GetCustomerByFilterEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getMeta());
    Assertions.assertEquals(response.getMeta().getOffset(), 1);
    Assertions.assertEquals(response.getMeta().getLimit(), 1);
    Assertions.assertNotNull(response.getCustomers());
    Assertions.assertEquals(response.getCustomers().size(), 1);
    Assertions.assertNotNull(response.getCustomers().get(0).getId());
    Assertions.assertEquals(response.getCustomers().get(0).getName(), "kendao");
    Assertions.assertEquals(response.getCustomers().get(0).getEmail(), "kendao@luizalabs.com");
  }

  @Test
  @Order(21)
  public void getAllIsNotFoundPageable() throws Throwable {
    super.getIsNotFound(this.path + "?offset=2&limit=1", "Customer list is empty");
  }

  @Test
  @Order(22)
  public void getAllByIdIsOk() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    GetCustomerByFilterEndpointResponse response =
        super.getIsOk(this.path + "?id=" + customer.getId(), GetCustomerByFilterEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getMeta());
    Assertions.assertEquals(response.getMeta().getOffset(), 1);
    Assertions.assertEquals(response.getMeta().getLimit(), 10);
    Assertions.assertNotNull(response.getCustomers());
    Assertions.assertEquals(response.getCustomers().size(), 1);
    Assertions.assertEquals(response.getCustomers().get(0).getId(), customer.getId());
    Assertions.assertEquals(response.getCustomers().get(0).getName(), customer.getName());
    Assertions.assertEquals(response.getCustomers().get(0).getEmail(), customer.getEmail());
  }

  @Test
  @Order(23)
  public void getAllByEmailIsOk() throws Throwable {
    GetCustomerByFilterEndpointResponse response =
        super.getIsOk(this.path + "?email=kendao@luizalabs.com", GetCustomerByFilterEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getMeta());
    Assertions.assertEquals(response.getMeta().getOffset(), 1);
    Assertions.assertEquals(response.getMeta().getLimit(), 10);
    Assertions.assertNotNull(response.getCustomers());
    Assertions.assertEquals(response.getCustomers().size(), 1);
    Assertions.assertNotNull(response.getCustomers().get(0).getId());
    Assertions.assertEquals(response.getCustomers().get(0).getName(), "kendao");
    Assertions.assertEquals(response.getCustomers().get(0).getEmail(), "kendao@luizalabs.com");
  }

  @Test
  @Order(24)
  public void getAllByNameIsOk() throws Throwable {
    GetCustomerByFilterEndpointResponse response =
        super.getIsOk(this.path + "?name=ken", GetCustomerByFilterEndpointResponse.class);

    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getMeta());
    Assertions.assertEquals(response.getMeta().getOffset(), 1);
    Assertions.assertEquals(response.getMeta().getLimit(), 10);
    Assertions.assertNotNull(response.getCustomers());
    Assertions.assertEquals(response.getCustomers().size(), 1);
    Assertions.assertNotNull(response.getCustomers().get(0).getId());
    Assertions.assertEquals(response.getCustomers().get(0).getName(), "kendao");
    Assertions.assertEquals(response.getCustomers().get(0).getEmail(), "kendao@luizalabs.com");
  }

  @Test
  @Order(25)
  public void deleteIsNoContentWithProducts() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    super.deleteIsNoContent(this.path + "/" + customer.getId());
  }
}
