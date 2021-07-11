package com.productstock.service.hype.exception.base;

import static java.util.Objects.nonNull;

import com.productstock.service.hype.exception.ExceptionMap;
import com.productstock.service.hype.exception.model.BusinessException;
import com.productstock.service.hype.exception.model.ExceptionRestResult;
import com.productstock.service.hype.exception.model.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public abstract class BaseExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  protected ExceptionRestResult handle(Throwable throwable) {
    var serviceException = new ServiceException(ExceptionMap.INTERNAL_SERVER_ERROR, throwable);
    log.error(generateLogMessage(serviceException), throwable);
    return new ExceptionRestResult(serviceException);
  }

  protected ExceptionRestResult handle(MethodArgumentNotValidException methodArgumentNotValidException) {
    var serviceException = new ServiceException(ExceptionMap.BAD_REQUEST, methodArgumentNotValidException,
        generateValidationMessage(methodArgumentNotValidException.getBindingResult().getFieldError()));
    log.error(generateLogMessage(serviceException), methodArgumentNotValidException);
    return new ExceptionRestResult(serviceException);
  }

  protected ExceptionRestResult handle(BaseException baseException) {
    if (baseException instanceof BusinessException) {
      log.error(generateLogMessage(baseException), baseException);
    } else {
      // May implement later if needed...
    }
    return new ExceptionRestResult(baseException);
  }

  private String generateLogMessage(BaseException baseException) {
    return String.format("An exception occurred with an exception_id: %s", baseException.getExceptionId());
  }

  private String generateValidationMessage(FieldError fieldError) {
    var message = "";
    if (nonNull(fieldError)) {
      message = String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage());
    }
    return message;
  }

}
