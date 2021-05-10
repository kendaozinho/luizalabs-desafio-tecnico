package com.luizalabs.customer.entrypoint.api.v1.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.interactor.customer.*;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.CreateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.request.UpdateCustomerEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.CreateCustomerEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByFilterEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.GetCustomerByIdEndpointResponse;
import com.luizalabs.customer.entrypoint.api.v1.customer.response.UpdateCustomerEndpointResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/v1/customers")
@Api(tags = {"Customer Endpoint"}, description = "/v1/customers")
public class CustomerEndpointImpl {
  private GetCustomerByIdInteractor getCustomerByIdInteractor;
  private GetCustomersByFilterInteractor getCustomersByFilterInteractor;
  private CreateCustomerInteractor createCustomerInteractor;
  private UpdateCustomerInteractor updateCustomerInteractor;
  private DeleteCustomerByIdInteractor deleteCustomerByIdInteractor;

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
  @ApiOperation(value = "Get Customer(s)")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid id | Invalid offset | Invalid limit"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer(s) not found"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public GetCustomerByFilterEndpointResponse getByFilter(
      @RequestParam(required = false) @ApiParam(name = "id", value = "id") UUID id,
      @RequestParam(required = false) @ApiParam(name = "name", value = "name") String name,
      @RequestParam(required = false) @ApiParam(name = "email", value = "email") String email,
      @RequestParam(required = false, defaultValue = "1") @ApiParam(name = "offset", value = "page number") @Min(1) Integer offset,
      @RequestParam(required = false, defaultValue = "10") @ApiParam(name = "limit", value = "page size") @Min(1) Integer limit
  ) {
    return new GetCustomerByFilterEndpointResponse(
        this.getCustomersByFilterInteractor.execute(id, name, email, offset, limit),
        offset, limit
    );
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Get Customer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid id"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer not found"),
          @ApiResponse(code = 500, message = "Internal Server Error"),
          @ApiResponse(code = 502, message = "Bad Gateway")
      }
  )
  public GetCustomerByIdEndpointResponse getById(
      @PathVariable @ApiParam(name = "id", value = "id") UUID id,
      @RequestParam(required = false, defaultValue = "true") @ApiParam(name = "expand", value = "show products") Boolean expand
  ) {
    return new GetCustomerByIdEndpointResponse(
        this.getCustomerByIdInteractor.execute(id, expand)
    );
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ApiOperation(value = "Create Customer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 201, message = "Created"),
          @ApiResponse(code = 400, message = "Invalid Request Body"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 409, message = "Email already exists"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public CreateCustomerEndpointResponse post(@RequestBody @Valid CreateCustomerEndpointRequest request) {
    Customer customer = this.createCustomerInteractor.execute(request.toEntity());
    return new CreateCustomerEndpointResponse(customer.getId(), customer.getName(), customer.getEmail());
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(value = "Update Customer")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "OK"),
          @ApiResponse(code = 400, message = "Invalid id | Invalid Request Body"),
          @ApiResponse(code = 401, message = "Unauthorized"),
          @ApiResponse(code = 404, message = "Customer not found"),
          @ApiResponse(code = 409, message = "Email already exists"),
          @ApiResponse(code = 500, message = "Internal Server Error")
      }
  )
  public UpdateCustomerEndpointResponse put(
      @PathVariable @ApiParam(name = "id", value = "id") UUID id,
      @RequestBody @Valid UpdateCustomerEndpointRequest request
  ) {
    Customer customer = this.updateCustomerInteractor.execute(id, request.toEntity());
    return new UpdateCustomerEndpointResponse(customer.getId(), customer.getName(), customer.getEmail());
  }

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
    this.deleteCustomerByIdInteractor.execute(id);
  }
}
