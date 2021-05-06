package com.luizalabs.customer.entrypoint.api.v1.customer.impl;

import com.luizalabs.customer.application.customer.*;
import com.luizalabs.customer.application.customer.request.CreateCustomerInteractorRequest;
import com.luizalabs.customer.application.customer.request.UpdateCustomerInteractorRequest;
import com.luizalabs.customer.application.customer.response.CreateCustomerInteractorResponse;
import com.luizalabs.customer.application.customer.response.GetCustomerByFilterInteractorResponse;
import com.luizalabs.customer.application.customer.response.GetCustomerByIdInteractorResponse;
import com.luizalabs.customer.application.customer.response.UpdateCustomerInteractorResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.CustomerEndpoint;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/customers")
public class CustomerEndpointImpl implements CustomerEndpoint {
  private GetCustomerByIdInteractor getCustomerByIdInteractor;
  private GetCustomerByFilterInteractor getCustomerByFilterInteractor;
  private CreateCustomerInteractor createCustomerInteractor;
  private UpdateCustomerInteractor updateCustomerInteractor;
  private DeleteCustomerInteractor deleteCustomerInteractor;

  public CustomerEndpointImpl(
      GetCustomerByIdInteractor getCustomerByIdInteractor,
      GetCustomerByFilterInteractor getCustomerByFilterInteractor,
      CreateCustomerInteractor createCustomerInteractor,
      UpdateCustomerInteractor updateCustomerInteractor,
      DeleteCustomerInteractor deleteCustomerInteractor
  ) {
    this.getCustomerByIdInteractor = getCustomerByIdInteractor;
    this.getCustomerByFilterInteractor = getCustomerByFilterInteractor;
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
  public GetCustomerByFilterInteractorResponse getByFilter(
      @RequestParam(required = false) @ApiParam(name = "id", value = "id") UUID id,
      @RequestParam(required = false) @ApiParam(name = "name", value = "name") String name,
      @RequestParam(required = false) @ApiParam(name = "email", value = "email") String email,
      @RequestParam(required = false) @ApiParam(name = "offset", value = "page number") Integer offset,
      @RequestParam(required = false) @ApiParam(name = "limit", value = "page size") Integer limit
  ) {
    return this.getCustomerByFilterInteractor.execute(id, name, email);
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
  public GetCustomerByIdInteractorResponse getById(@PathVariable @ApiParam(name = "id", value = "id") UUID id) {
    return this.getCustomerByIdInteractor.execute(id);
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
  public CreateCustomerInteractorResponse post(@RequestBody CreateCustomerInteractorRequest request) {
    return this.createCustomerInteractor.execute(request);
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
  public UpdateCustomerInteractorResponse put(
      @PathVariable @ApiParam(name = "id", value = "id") UUID id,
      @RequestBody UpdateCustomerInteractorRequest request
  ) {
    return this.updateCustomerInteractor.execute(id, request);
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
