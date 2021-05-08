package com.luizalabs.customer.entrypoint.api.advice;

import com.luizalabs.customer.domain.exception.*;
import com.luizalabs.customer.domain.exception.dto.BaseResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomRestControllerAdvice {
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BaseResponseError catchBadRequestException(Throwable t) {
    return new BaseResponseError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), t.getMessage());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public BaseResponseError catchNotFoundException(Throwable t) {
    return new BaseResponseError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), t.getMessage());
  }

  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public BaseResponseError catchConflictException(Throwable t) {
    return new BaseResponseError(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), t.getMessage());
  }

  @ExceptionHandler({InternalServerErrorException.class, Throwable.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponseError catchInternalServerErrorException(Throwable t) {
    return new BaseResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), t.getMessage());
  }

  @ExceptionHandler(BadGatewayException.class)
  @ResponseStatus(HttpStatus.BAD_GATEWAY)
  public BaseResponseError catchBadGatewayException(Throwable t) {
    return new BaseResponseError(HttpStatus.BAD_GATEWAY.value(), HttpStatus.BAD_GATEWAY.getReasonPhrase(), t.getMessage());
  }
}
