package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.domain.gateway.customer.DeleteCustomerGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.DeleteCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductsByCustomerIdGateway;
import com.luizalabs.customer.domain.interactor.customer.DeleteCustomerInteractor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class DeleteCustomerInteractorImpl implements DeleteCustomerInteractor {
  DeleteCustomerGateway deleteCustomerGateway;
  GetCustomerProductsByCustomerIdGateway getCustomerProductsByCustomerIdGateway;
  DeleteCustomerProductGateway deleteCustomerProductGateway;

  public DeleteCustomerInteractorImpl(
      DeleteCustomerGateway deleteCustomerGateway,
      GetCustomerProductsByCustomerIdGateway getCustomerProductsByCustomerIdGateway,
      DeleteCustomerProductGateway deleteCustomerProductGateway
  ) {
    this.deleteCustomerGateway = deleteCustomerGateway;
    this.getCustomerProductsByCustomerIdGateway = getCustomerProductsByCustomerIdGateway;
    this.deleteCustomerProductGateway = deleteCustomerProductGateway;
  }

  @Override
  @Transactional
  public void execute(UUID id) {
    this.getCustomerProductsByCustomerIdGateway.getAllByCustomerId(id).forEach(
        customerProduct -> this.deleteCustomerProductGateway.delete(customerProduct.getCustomerId(), customerProduct.getProductId())
    );

    this.deleteCustomerGateway.delete(id);
  }
}
