package com.productstock.service.hype.exception;

import com.productstock.service.hype.exception.base.BaseExceptionHandler;
import com.productstock.service.hype.exception.model.BusinessException;
import com.productstock.service.hype.exception.model.ExceptionRestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler extends BaseExceptionHandler {

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ExceptionRestResult> handleRuntimeException(Throwable throwable) {
    ExceptionRestResult exceptionRestResult = handle(throwable);
    return ResponseEntity.status(HttpStatus.valueOf(exceptionRestResult.getStatus()))
        .body(exceptionRestResult);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ExceptionRestResult> handleRuntimeException(
      BusinessException businessException) {
    ExceptionRestResult exceptionRestResult = handle(businessException);
    return ResponseEntity.status(HttpStatus.valueOf(exceptionRestResult.getStatus()))
        .body(exceptionRestResult);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionRestResult> handleRuntimeException(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    ExceptionRestResult exceptionRestResult = handle(methodArgumentNotValidException);
    return ResponseEntity.status(HttpStatus.valueOf(exceptionRestResult.getStatus()))
        .body(exceptionRestResult);
  }

}
