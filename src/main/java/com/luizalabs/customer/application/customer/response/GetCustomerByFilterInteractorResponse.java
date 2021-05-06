package com.luizalabs.customer.application.customer.response;

import com.luizalabs.customer.domain.database.table.CustomerTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerByFilterInteractorResponse {
  private MetaResponse meta;
  private ArrayList<CustomerResponse> customers;

  public GetCustomerByFilterInteractorResponse(ArrayList<CustomerTable> customers) {
    this.meta = new MetaResponse(0, 100, customers.size());
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
  public class MetaResponse {
    private Integer offset;
    private Integer limit;
    private Integer size;
  }

  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public class CustomerResponse {
    private UUID id;
    private String name;
    private String email;
  }
}

