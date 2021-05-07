package com.luizalabs.customer.entrypoint.api.v1.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.interactor.customer.*;
import com.luizalabs.customer.entrypoint.api.v1.customer.CustomerEndpoint;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByFilterEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByIdEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/customers")
@Api(tags = {"Customer Endpoint"}, description = "/v1/customers")
public class CustomerEndpointImpl implements CustomerEndpoint {
  private GetCustomerByIdInteractor getCustomerByIdInteractor;
  private GetCustomersByFilterInteractor getCustomersByFilterInteractor;
  private CreateCustomerInteractor createCustomerInteractor;
  private UpdateCustomerInteractor updateCustomerInteractor;
  private DeleteCustomerInteractor deleteCustomerInteractor;

  public CustomerEndpointImpl(
      GetCustomerByIdInteractor getCustomerByIdInteractor,
      GetCustomersByFilterInteractor getCustomersByFilterInteractor,
      CreateCustomerInteractor createCustomerInteractor,
      UpdateCustomerInteractor updateCustomerInteractor,
      DeleteCustomerInteractor deleteCustomerInteractor
  ) {
    this.getCustomerByIdInteractor = getCustomerByIdInteractor;
    this.getCustomersByFilterInteractor = getCustomersByFilterInteractor;
    this.createCustomerInteractor = createCustomerInteractor;
    this.updateCustomerInteractor = updateCustomerInteractor;
    this.deleteCustomerInteractor = deleteCustomerInteractor;
  }

  @Override
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Customer(s)")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer not found | Data not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public GetCustomerByFilterEndpointResponse getByFilter(
      @RequestParam(required = false) @ApiParam(name = "id", value = "id") UUID id,
      @RequestParam(required = false) @ApiParam(name = "name", value = "name") String name,
      @RequestParam(required = false) @ApiParam(name = "email", value = "email") String email,
      @RequestParam(required = false) @ApiParam(name = "offset", value = "page number") Integer offset,
      @RequestParam(required = false) @ApiParam(name = "limit", value = "page size") Integer limit
  ) {
    return new GetCustomerByFilterEndpointResponse(
        this.getCustomersByFilterInteractor.execute(id, name, email)
    );
  }

  @Override
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Customer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public GetCustomerByIdEndpointResponse getById(
      @PathVariable @ApiParam(name = "id", value = "id") UUID id,
      @RequestParam(required = false, defaultValue = "false") @ApiParam(name = "expand", value = "show products") Boolean expand
  ) {
    return new GetCustomerByIdEndpointResponse(
        this.getCustomerByIdInteractor.execute(id, expand)
    );
  }

  @Override
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create Customer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 409, message = "Email already exists"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public CreateCustomerEndpointResponse post(@RequestBody CreateCustomerEndpointRequest request) {
    Customer customer = this.createCustomerInteractor.execute(request.toEntity());
    return new CreateCustomerEndpointResponse(customer.getId(), customer.getName(), customer.getEmail());
  }

  @Override
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update Customer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer not found"),
          @ApiResponse(code = 409, message = "Email already exists"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public UpdateCustomerEndpointResponse put(
      @PathVariable @ApiParam(name = "id", value = "id") UUID id,
      @RequestBody UpdateCustomerEndpointRequest request
  ) {
    Customer customer = this.updateCustomerInteractor.execute(id, request.toEntity());
    return new UpdateCustomerEndpointResponse(customer.getId(), customer.getName(), customer.getEmail());
  }

  @Override
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ApiOperation(value = "Delete Customer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 204, message = "No Content"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public void delete(@PathVariable @ApiParam(name = "id", value = "id") UUID id) {
    this.deleteCustomerInteractor.execute(id);
  }
}
