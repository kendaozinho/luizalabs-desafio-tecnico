package com.luizalabs.customer.entrypoint.api.v1.customer.impl;

import com.luizalabs.customer.entrypoint.api.v1.customer.CustomerEndpoint;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/v1/customers")
public class CustomerEndpointImpl implements CustomerEndpoint {
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
  public ArrayList<GetCustomerEndpointResponse> getAll(
      @RequestParam(required = false) @ApiParam(name = "id", value = "id") UUID id,
      @RequestParam(required = false) @ApiParam(name = "name", value = "name") String name,
      @RequestParam(required = false) @ApiParam(name = "email", value = "email") String email,
      @RequestParam(required = false) @ApiParam(name = "offset", value = "page number") Integer offset,
      @RequestParam(required = false) @ApiParam(name = "limit", value = "page size") Integer limit
  ) {
    return null;
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
  public GetCustomerEndpointResponse getById(@PathVariable @ApiParam(name = "id", value = "id") UUID id) {
    return null;
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
    return null;
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
    return null;
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

  }
}
