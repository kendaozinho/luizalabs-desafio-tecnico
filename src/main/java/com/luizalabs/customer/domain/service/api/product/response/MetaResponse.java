package com.luizalabs.customer.domain.service.api.product.response;

public class MetaResponse {
  // @JsonProperty("page_number")
  private Long pageNumber;
  // @JsonProperty("page_size")
  private Long pageSize;

  public MetaResponse() {
  }

  public Long getPageNumber() {
    return this.pageNumber;
  }

  public Long getPageSize() {
    return this.pageSize;
  }
}
