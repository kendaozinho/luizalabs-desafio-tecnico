package com.luizalabs.customer.entrypoint.api.v1.customer.response;

import com.luizalabs.customer.domain.entity.Customer;

import java.util.ArrayList;
import java.util.UUID;

public class GetCustomerByFilterEndpointResponse {
  private MetaEndpointResponse meta;
  private ArrayList<CustomerEndpointResponse> customers;

  public GetCustomerByFilterEndpointResponse() {
  }

  public GetCustomerByFilterEndpointResponse(ArrayList<Customer> customers, Integer pageNumber, Integer pageSize) {
    this.meta = new MetaEndpointResponse(pageNumber, pageSize);
    this.customers = new ArrayList<>();
    customers.forEach(customer ->
        this.customers.add(
            new CustomerEndpointResponse(customer.getId(), customer.getName(), customer.getEmail())
        )
    );
  }

  public MetaEndpointResponse getMeta() {
    return this.meta;
  }

  public ArrayList<CustomerEndpointResponse> getCustomers() {
    return this.customers;
  }

  public static class MetaEndpointResponse {
    private Integer offset;
    private Integer limit;

    public MetaEndpointResponse() {
    }

    public MetaEndpointResponse(Integer offset, Integer limit) {
      this.offset = offset;
      this.limit = limit;
    }

    public Integer getOffset() {
      return this.offset;
    }

    public Integer getLimit() {
      return this.limit;
    }
  }

  public static class CustomerEndpointResponse {
    private UUID id;
    private String name;
    private String email;

    public CustomerEndpointResponse() {
    }

    public CustomerEndpointResponse(UUID id, String name, String email) {
      this.id = id;
      this.name = name;
      this.email = email;
    }

    public UUID getId() {
      return this.id;
    }

    public String getName() {
      return this.name;
    }

    public String getEmail() {
      return this.email;
    }
  }
}

