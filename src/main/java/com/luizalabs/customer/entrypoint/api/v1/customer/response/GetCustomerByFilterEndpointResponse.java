package com.luizalabs.customer.entrypoint.api.v1.customer.response;

import com.luizalabs.customer.domain.entity.Customer;

import java.util.ArrayList;
import java.util.UUID;

public class GetCustomerByFilterEndpointResponse {
  private MetaResponse meta;
  private ArrayList<CustomerResponse> customers;

  public GetCustomerByFilterEndpointResponse() {
  }

  public GetCustomerByFilterEndpointResponse(ArrayList<Customer> customers, Integer pageNumber, Integer pageSize) {
    this.meta = new MetaResponse(pageNumber, pageSize);
    this.customers = new ArrayList<>();
    customers.forEach(customer ->
        this.customers.add(
            new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail())
        )
    );
  }

  public MetaResponse getMeta() {
    return this.meta;
  }

  public ArrayList<CustomerResponse> getCustomers() {
    return this.customers;
  }

  public static class MetaResponse {
    private Integer offset;
    private Integer limit;

    public MetaResponse() {
    }

    public MetaResponse(Integer offset, Integer limit) {
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

  public static class CustomerResponse {
    private UUID id;
    private String name;
    private String email;

    public CustomerResponse() {
    }

    public CustomerResponse(UUID id, String name, String email) {
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

