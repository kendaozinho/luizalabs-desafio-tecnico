package com.luizalabs.customer.entrypoint.api.v1.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.interactor.customer.*;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByFilterEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByIdEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/v1/customers")
@Tag(name = "Customer Endpoint", description = "/v1/customers")
public class CustomerEndpointImpl {
  private final GetCustomerByIdInteractor getCustomerByIdInteractor;
  private final GetCustomersByFilterInteractor getCustomersByFilterInteractor;
  private final CreateCustomerInteractor createCustomerInteractor;
  private final UpdateCustomerInteractor updateCustomerInteractor;
  private final DeleteCustomerByIdInteractor deleteCustomerByIdInteractor;

  public CustomerEndpointImpl(
      GetCustomerByIdInteractor getCustomerByIdInteractor,
      GetCustomersByFilterInteractor getCustomersByFilterInteractor,
      CreateCustomerInteractor createCustomerInteractor,
      UpdateCustomerInteractor updateCustomerInteractor,
      DeleteCustomerByIdInteractor deleteCustomerByIdInteractor
  ) {
    this.getCustomerByIdInteractor = getCustomerByIdInteractor;
    this.getCustomersByFilterInteractor = getCustomersByFilterInteractor;
    this.createCustomerInteractor = createCustomerInteractor;
    this.updateCustomerInteractor = updateCustomerInteractor;
    this.deleteCustomerByIdInteractor = deleteCustomerByIdInteractor;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get Customer(s)")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid id | Invalid offset | Invalid limit"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer(s) not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public GetCustomerByFilterEndpointResponse getByFilter(
      @RequestParam(required = false) @Parameter(name = "id", description = "id") UUID id,
      @RequestParam(required = false) @Parameter(name = "name", description = "name") String name,
      @RequestParam(required = false) @Parameter(name = "email", description = "email") String email,
      @RequestParam(required = false, defaultValue = "1") @Parameter(name = "offset", description = "page number") @Min(1) Integer offset,
      @RequestParam(required = false, defaultValue = "10") @Parameter(name = "limit", description = "page size") @Min(1) Integer limit
  ) {
    return new GetCustomerByFilterEndpointResponse(
        this.getCustomersByFilterInteractor.execute(id, name, email, offset, limit),
        offset, limit
    );
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get Customer")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid id"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error"),
          @ApiResponse(responseCode = "502", description = "Bad Gateway")
      }
  )
  public GetCustomerByIdEndpointResponse getById(
      @PathVariable @Parameter(name = "id", description = "id") UUID id,
      @RequestParam(required = false, defaultValue = "true") @Parameter(name = "expand", description = "show products") Boolean expand
  ) {
    return new GetCustomerByIdEndpointResponse(
        this.getCustomerByIdInteractor.execute(id, expand)
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create Customer")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "201", description = "Created"),
          @ApiResponse(responseCode = "400", description = "Invalid Request Body"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "409", description = "Email already exists"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public CreateCustomerEndpointResponse post(@RequestBody @Valid CreateCustomerEndpointRequest request) {
    Customer customer = this.createCustomerInteractor.execute(request.toEntity());
    return new CreateCustomerEndpointResponse(customer.getId(), customer.getName(), customer.getEmail());
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Update Customer")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "OK"),
          @ApiResponse(responseCode = "400", description = "Invalid id | Invalid Request Body"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer not found"),
          @ApiResponse(responseCode = "409", description = "Email already exists"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public UpdateCustomerEndpointResponse put(
      @PathVariable @Parameter(name = "id", description = "id") UUID id,
      @RequestBody @Valid UpdateCustomerEndpointRequest request
  ) {
    Customer customer = this.updateCustomerInteractor.execute(id, request.toEntity());
    return new UpdateCustomerEndpointResponse(customer.getId(), customer.getName(), customer.getEmail());
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Delete Customer")
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "204", description = "No Content"),
          @ApiResponse(responseCode = "400", description = "Invalid id"),
          @ApiResponse(responseCode = "401", description = "Unauthorized"),
          @ApiResponse(responseCode = "404", description = "Customer not found"),
          @ApiResponse(responseCode = "500", description = "Internal Server Error")
      }
  )
  public void delete(@PathVariable @Parameter(name = "id", description = "id") UUID id) {
    this.deleteCustomerByIdInteractor.execute(id);
  }
}
