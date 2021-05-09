package com.luizalabs.customer.entrypoint.api.v1.customer.response;

import com.luizalabs.customer.domain.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerByFilterEndpointResponse {
  private MetaResponse meta;
  private ArrayList<CustomerResponse> customers;

  public GetCustomerByFilterEndpointResponse(ArrayList<Customer> customers, Integer pageNumber, Integer pageSize) {
    this.meta = new MetaResponse(pageNumber, pageSize);
    this.customers = new ArrayList<>();
    customers.forEach(customer ->
        this.customers.add(
            new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail())
        )
    );
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MetaResponse {
    private Integer offset;
    private Integer limit;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CustomerResponse {
    private UUID id;
    private String name;
    private String email;
  }
}

