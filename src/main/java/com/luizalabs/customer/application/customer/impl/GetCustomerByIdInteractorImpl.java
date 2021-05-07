package com.luizalabs.customer.application.customer.impl;

import com.luizalabs.customer.domain.entity.Customer;
import com.luizalabs.customer.domain.entity.CustomerProduct;
import com.luizalabs.customer.domain.exception.NotFoundException;
import com.luizalabs.customer.domain.gateway.customer.GetCustomerByIdGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.DeleteCustomerProductGateway;
import com.luizalabs.customer.domain.gateway.customerproduct.GetCustomerProductsByCustomerIdGateway;
import com.luizalabs.customer.domain.gateway.product.GetProductByIdGateway;
import com.luizalabs.customer.domain.interactor.customer.GetCustomerByIdInteractor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class GetCustomerByIdInteractorImpl implements GetCustomerByIdInteractor {
  private GetCustomerByIdGateway getCustomerByIdGateway;
  private GetCustomerProductsByCustomerIdGateway getCustomerProductsByCustomerIdGateway;
  private GetProductByIdGateway getProductByIdGateway;
  private DeleteCustomerProductGateway deleteCustomerProductGateway;

  public GetCustomerByIdInteractorImpl(
      GetCustomerByIdGateway getCustomerByIdGateway,
      GetCustomerProductsByCustomerIdGateway getCustomerProductsByCustomerIdGateway,
      GetProductByIdGateway getProductByIdGateway,
      DeleteCustomerProductGateway deleteCustomerProductGateway
  ) {
    this.getCustomerByIdGateway = getCustomerByIdGateway;
    this.getCustomerProductsByCustomerIdGateway = getCustomerProductsByCustomerIdGateway;
    this.getProductByIdGateway = getProductByIdGateway;
    this.deleteCustomerProductGateway = deleteCustomerProductGateway;
  }

  @Override
  public Customer execute(UUID id, Boolean showProducts) {
    Customer customer = this.getCustomerByIdGateway.getOneById(id);

    if (showProducts) {
      ArrayList<CustomerProduct> customerProducts;

      try {
        customerProducts = this.getCustomerProductsByCustomerIdGateway.getAllByCustomerId(id);
      } catch (NotFoundException exception) {
        customerProducts = new ArrayList<>();
      }

      customer.setProducts(new ArrayList<>());
      customerProducts.forEach(customerProduct -> {
        try {
          customer.getProducts().add(
              this.getProductByIdGateway.getOneById(customerProduct.getProductId())
          );
        } catch (NotFoundException exception) {
          // This product is no longer available
          this.deleteCustomerProductGateway.delete(customerProduct.getCustomerId(), customerProduct.getProductId());
        }
      });
    }

    return customer;
  }
}
