package com.luizalabs.customer.domain.interactor.customer;

import java.util.UUID;

public interface DeleteCustomerInteractor {
  void execute(UUID id);
}
