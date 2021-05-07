package com.luizalabs.customer.entrypoint.api.v1.customerproduct.impl;

import com.luizalabs.customer.domain.interactor.customerproduct.CreateCustomerProductInteractor;
import com.luizalabs.customer.domain.interactor.customerproduct.DeleteCustomerProductInteractor;
import com.luizalabs.customer.domain.interactor.customerproduct.GetCustomerProductInteractor;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.CustomerProductEndpoint;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.request.CreateCustomerProductEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.CreateCustomerProductEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.GetCustomerProductEndpointResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/customers")
@Api(tags = {"Customer Product Endpoint"}, description = "/v1/customers/products")
public class CustomerProductEndpointImpl implements CustomerProductEndpoint {
  private GetCustomerProductInteractor getCustomerProductInteractor;
  private CreateCustomerProductInteractor createCustomerProductInteractor;
  private DeleteCustomerProductInteractor deleteCustomerProductInteractor;

  public CustomerProductEndpointImpl(
      GetCustomerProductInteractor getCustomerProductInteractor,
      CreateCustomerProductInteractor createCustomerProductInteractor,
      DeleteCustomerProductInteractor deleteCustomerProductInteractor
  ) {
    this.getCustomerProductInteractor = getCustomerProductInteractor;
    this.createCustomerProductInteractor = createCustomerProductInteractor;
    this.deleteCustomerProductInteractor = deleteCustomerProductInteractor;
  }

  @GetMapping("/{customerId}/products/{productId}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Customer Product")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid customerId | Invalid productId"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer Product not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public GetCustomerProductEndpointResponse get(
      @PathVariable @ApiParam(name = "customerId", value = "customer id") UUID customerId,
      @PathVariable @ApiParam(name = "productId", value = "product id") UUID productId
  ) {
    return this.getCustomerProductInteractor.execute(customerId, productId);
  }

  @PostMapping("/products")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create Customer Product")
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer not found | Product not found"),
          @ApiResponse(code = 409, message = "Customer Product already exists"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public CreateCustomerProductEndpointResponse post(@RequestBody CreateCustomerProductEndpointRequest request) {
    return this.createCustomerProductInteractor.execute(request);
  }

  @DeleteMapping("/{customerId}/products/{productId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete Customer Product")
  @ApiResponses(
      value = {
          @ApiResponse(code = 204, message = "No Content"),
          @ApiResponse(code = 400, message = "Invalid customerId | Invalid productId"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer Product not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public void delete(
      @PathVariable @ApiParam(name = "customerId", value = "customer id") UUID customerId,
      @PathVariable @ApiParam(name = "productId", value = "product id") UUID productId
  ) {
    this.deleteCustomerProductInteractor.execute(customerId, productId);
  }
}