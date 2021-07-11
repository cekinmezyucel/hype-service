package com.productstock.service.hype.exception.model;

import com.productstock.service.hype.exception.base.BaseException;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionRestResult implements Serializable {

  private String exceptionId;
  private Integer status;
  private String message;

  public ExceptionRestResult(BaseException baseException) {
    this.exceptionId = baseException.getExceptionId();
    this.status = baseException.getStatus();
    this.message = baseException.getErrorMessage();
  }

}
