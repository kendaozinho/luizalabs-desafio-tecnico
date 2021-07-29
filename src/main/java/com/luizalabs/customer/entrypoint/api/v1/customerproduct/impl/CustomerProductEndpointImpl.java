package com.luizalabs.customer.entrypoint.api.v1.customerproduct.impl;

import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.interactor.customerproduct.CreateCustomerProductInteractor;
import com.luizalabs.customer.domain.interactor.customerproduct.DeleteCustomerProductInteractor;
import com.luizalabs.customer.domain.interactor.customerproduct.GetCustomerProductInteractor;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.request.CreateCustomerProductEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.CreateCustomerProductEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.GetCustomerProductEndpointResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/v1/customers")
@Tag(name = "Customer Product Endpoint", description = "/v1/customers/products")
public class CustomerProductEndpointImpl {
  private final GetCustomerProductInteractor getCustomerProductInteractor;
  private final CreateCustomerProductInteractor createCustomerProductInteractor;
  private final DeleteCustomerProductInteractor deleteCustomerProductInteractor;

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
  @Operation(summary = "Get Customer Product")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid customerId | Invalid productId"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer Product not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public GetCustomerProductEndpointResponse get(
      @PathVariable @Parameter(name = "customerId", description = "customer id") UUID customerId,
      @PathVariable @Parameter(name = "productId", description = "product id") UUID productId
  ) {
    CustomerProduct customerProduct = this.getCustomerProductInteractor.execute(customerId, productId);
    return new GetCustomerProductEndpointResponse(customerProduct.getCustomerId(), customerProduct.getProductId());
  }

  @PostMapping("/products")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create Customer Product")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Created"),
          @ApiResponse(responseCode = "400", description = "Invalid Request Body"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer not found | Product not found"),
          @ApiResponse(responseCode = "409", description = "Customer Product already exists"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "502", description = "Bad Gateway")
      }
  )
  public CreateCustomerProductEndpointResponse post(@RequestBody @Valid CreateCustomerProductEndpointRequest request) {
    CustomerProduct customerProduct = this.createCustomerProductInteractor.execute(request.toEntity());
    return new CreateCustomerProductEndpointResponse(customerProduct.getCustomerId(), customerProduct.getProductId());
  }

  @DeleteMapping("/{customerId}/products/{productId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Delete Customer Product")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "No Content"),
          @ApiResponse(responseCode = "400", description = "Invalid customerId | Invalid productId"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer Product not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public void delete(
      @PathVariable @Parameter(name = "customerId", description = "customer id") UUID customerId,
      @PathVariable @Parameter(name = "productId", description = "product id") UUID productId
  ) {
    this.deleteCustomerProductInteractor.execute(customerId, productId);
  }
}