package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.domain.exception.NotFoundException;
import com.luizalabs.customer.domain.gateway.customer.DeleteCustomerGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.DeleteCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductsByCustomerIdGateway;
import com.luizalabs.customer.domain.interactor.customer.DeleteCustomerByIdInteractor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class DeleteCustomerByIdInteractorImpl implements DeleteCustomerByIdInteractor {
  DeleteCustomerGateway deleteCustomerGateway;
  GetCustomerProductsByCustomerIdGateway getCustomerProductsByCustomerIdGateway;
  DeleteCustomerProductGateway deleteCustomerProductGateway;

  public DeleteCustomerByIdInteractorImpl(
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
    try {
      this.getCustomerProductsByCustomerIdGateway.getAllByCustomerId(id).forEach(
          customerProduct -> this.deleteCustomerProductGateway.delete(customerProduct.getCustomerId(), customerProduct.getProductId())
      );
    } catch (NotFoundException exception) {
      // Ignore this exception
    }

    this.deleteCustomerGateway.delete(id);
  }
}
