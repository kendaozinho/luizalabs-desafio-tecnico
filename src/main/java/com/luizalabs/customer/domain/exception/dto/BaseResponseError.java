package com.luizalabs.customer.domain.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseError {
  private Integer code;
  private String message;
  private String details;
}
