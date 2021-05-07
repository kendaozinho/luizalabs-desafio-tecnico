package com.luizalabs.customer.domain.interactor.customer;

import java.util.UUID;

public interface DeleteCustomerByIdInteractor {
  void execute(UUID id);
}
