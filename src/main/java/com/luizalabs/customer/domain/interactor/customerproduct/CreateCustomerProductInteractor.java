package com.luizalabs.customer.domain.interactor.customerproduct;

import com.luizalabs.customer.entrypoint.api.v1.customerproduct.request.CreateCustomerProductEndpointRequest;
import com.luizalabs.customer.entrypoint.api.v1.customerproduct.response.CreateCustomerProductEndpointResponse;

public interface CreateCustomerProductInteractor {
  CreateCustomerProductEndpointResponse execute(CreateCustomerProductEndpointRequest request);
}
