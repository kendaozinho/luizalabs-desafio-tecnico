package com.luizalabs.customer.domain.gateway.customer;

import java.util.UUID;

public interface DeleteCustomerGateway {
  void delete(UUID id);
}
