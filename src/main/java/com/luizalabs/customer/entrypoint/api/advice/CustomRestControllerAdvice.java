package com.luizalabs.customer.entrypoint.api.advice;

import com.luizalabs.customer.domain.exception.BadRequestException;
import com.luizalabs.customer.domain.exception.ConflictException;
import com.luizalabs.customer.domain.exception.InternalServerErrorException;
import com.luizalabs.customer.domain.exception.NotFoundException;
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
    return new BaseResponseError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), t.toString());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public BaseResponseError catchNotFoundException(Throwable t) {
    return new BaseResponseError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), t.toString());
  }

  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public BaseResponseError catchConflictException(Throwable t) {
    return new BaseResponseError(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), t.toString());
  }

  @ExceptionHandler({InternalServerErrorException.class, Throwable.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public BaseResponseError catchInternalServerErrorException(Throwable t) {
    return new BaseResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), t.toString());
  }
}
