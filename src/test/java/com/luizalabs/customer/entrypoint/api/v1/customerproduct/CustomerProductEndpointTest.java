package com.luizalabs.customer.entrypoint.api.v1.customerproduct;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.gateway.customer.CreateCustomerGateway;
import com.luizalabs.customer.domain.gateway.customer.DeleteAllCustomersGateway;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByEmailGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.DeleteAllCustomerProductsGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductsByCustomerIdGateway;
import com.luizalabs.customer.entrypoint.api.base.BaseEndpointTest;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.request.CreateCustomerProductEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.CreateCustomerProductEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.GetCustomerProductEndpointResponse;
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
public class CustomerProductEndpointTest extends BaseEndpointTest {
  private String path = "/v1/customers/{customerId}/products/{productId}";
  @Autowired
  private GetCustomerByEmailGateway getCustomerByEmailGateway;
  @Autowired
  private CreateCustomerGateway createCustomerGateway;
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
  public void getByIdIsNotFound() throws Throwable {
    super.getIsNotFound(
        this.path.replace("{customerId}", UUID.randomUUID().toString()).replace("{productId}", UUID.randomUUID().toString()),
        "Customer Product(s) not found"
    );
  }

  @Test
  @Order(2)
  public void postIsCustomerNotFound() throws Throwable {
    CreateCustomerProductEndpointRequest request = new CreateCustomerProductEndpointRequest(
        UUID.randomUUID(), UUID.randomUUID()
    );

    super.postIsNotFound(
        this.path.replace("{customerId}", "").replace("{productId}", ""),
        request,
        "Customer(s) not found"
    );
  }

  @Test
  @Order(3)
  public void postIsProductNotFound() throws Throwable {
    Customer customer = this.createCustomerGateway.create(
        new Customer(null, "kendao", "kendao@luizalabs.com")
    );

    CreateCustomerProductEndpointRequest request = new CreateCustomerProductEndpointRequest(
        customer.getId(), UUID.randomUUID()
    );

    RestTemplate restTemplate = this.restTemplateBuilder.build();
    Mockito.doThrow(
        new HttpClientErrorException(
            HttpStatus.NOT_FOUND,
            "Not Found",
            ("{\"error\": \"Product " + request.getProductId() + " not found\"}").getBytes(),
            Charset.defaultCharset()
        )
    ).when(restTemplate).getForEntity("/" + request.getProductId() + "/", ProductApiResponse.class);

    super.postIsNotFound(
        this.path.replace("{customerId}", "").replace("{productId}", ""),
        request,
        "Product " + request.getProductId() + " not found"
    );
  }

  @Test
  @Order(4)
  public void postIsProductWithInvalidStatus() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    CreateCustomerProductEndpointRequest request = new CreateCustomerProductEndpointRequest(
        customer.getId(), UUID.randomUUID()
    );

    RestTemplate restTemplate = this.restTemplateBuilder.build();
    Mockito.doThrow(
        new HttpServerErrorException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error",
            ("{\"error\": \"Unable to get product " + request.getProductId() + "\"}").getBytes(),
            Charset.defaultCharset()
        )
    ).when(restTemplate).getForEntity("/" + request.getProductId() + "/", ProductApiResponse.class);

    super.postIsInternalServerError(
        this.path.replace("{customerId}", "").replace("{productId}", ""),
        request,
        "Unable to get product " + request.getProductId()
    );
  }

  @Test
  @Order(5)
  public void postIsProductWithUnexpectedError() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    CreateCustomerProductEndpointRequest request = new CreateCustomerProductEndpointRequest(
        customer.getId(), UUID.randomUUID()
    );

    RestTemplate restTemplate = this.restTemplateBuilder.build();
    Mockito.doThrow(
        new RuntimeException("Unable to connect to server")
    ).when(restTemplate).getForEntity("/" + request.getProductId() + "/", ProductApiResponse.class);

    super.postIsBadGateway(
        this.path.replace("{customerId}", "").replace("{productId}", ""),
        request,
        "Unexpected error on get product " + request.getProductId() + ": java.lang.RuntimeException: Unable to connect to server"
    );
  }

  @Test
  @Order(6)
  public void postIsCreated() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");

    CreateCustomerProductEndpointRequest request =
        new CreateCustomerProductEndpointRequest(customer.getId(), UUID.randomUUID());

    RestTemplate restTemplate = this.restTemplateBuilder.build();
    Mockito.doReturn(
        new ResponseEntity<>(
            new ProductApiResponse(
                request.getProductId(),
                "Notebook",
                new BigDecimal("3199.99"),
                "http://www.images.com.br/" + request.getProductId() + ".jpg",
                "Dell",
                null
            ),
            HttpStatus.OK
        )
    ).when(restTemplate).getForEntity("/" + request.getProductId() + "/", ProductApiResponse.class);

    CreateCustomerProductEndpointResponse response = super.postIsCreated(
        this.path.replace("{customerId}", "").replace("{productId}", ""),
        request,
        CreateCustomerProductEndpointResponse.class
    );

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getCustomerId(), request.getCustomerId());
    Assertions.assertEquals(response.getProductId(), request.getProductId());
  }

  @Test
  @Order(7)
  public void postIsConflict() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");
    ArrayList<CustomerProduct> customerProducts =
        this.getCustomerProductsByCustomerIdGateway.getAllByCustomerId(customer.getId());

    CreateCustomerProductEndpointRequest request =
        new CreateCustomerProductEndpointRequest(customer.getId(), customerProducts.get(0).getProductId());

    RestTemplate restTemplate = this.restTemplateBuilder.build();
    Mockito.doReturn(
        new ResponseEntity<>(
            new ProductApiResponse(
                request.getProductId(),
                "Notebook",
                new BigDecimal("2550.50"),
                "http://www.images.com.br/" + request.getProductId() + ".jpg",
                "Samsung",
                null
            ),
            HttpStatus.OK
        )
    ).when(restTemplate).getForEntity("/" + request.getProductId() + "/", ProductApiResponse.class);

    super.postIsConflict(
        this.path.replace("{customerId}", "").replace("{productId}", ""),
        request,
        "Customer Product already exists"
    );
  }

  @Test
  @Order(8)
  public void getByIdIsOk() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");
    ArrayList<CustomerProduct> customerProducts =
        this.getCustomerProductsByCustomerIdGateway.getAllByCustomerId(customer.getId());

    GetCustomerProductEndpointResponse response = super.getIsOk(
        this.path.replace("{customerId}", customer.getId().toString()).replace("{productId}", customerProducts.get(0).getProductId().toString()),
        GetCustomerProductEndpointResponse.class
    );

    Assertions.assertNotNull(response);
    Assertions.assertEquals(response.getCustomerId(), customer.getId());
    Assertions.assertEquals(response.getProductId(), customerProducts.get(0).getProductId());
  }

  @Test
  @Order(9)
  public void deleteIsNotFound() throws Throwable {
    super.deleteIsNotFound(
        this.path.replace("{customerId}", UUID.randomUUID().toString()).replace("{productId}", UUID.randomUUID().toString()),
        "Customer Product(s) not found"
    );
  }

  @Test
  @Order(10)
  public void deleteIsNoContent() throws Throwable {
    Customer customer = this.getCustomerByEmailGateway.getOneByEmail("kendao@luizalabs.com");
    ArrayList<CustomerProduct> customerProducts =
        this.getCustomerProductsByCustomerIdGateway.getAllByCustomerId(customer.getId());

    super.deleteIsNoContent(
        this.path.replace("{customerId}", customer.getId().toString()).replace("{productId}", customerProducts.get(0).getProductId().toString())
    );
  }
}
