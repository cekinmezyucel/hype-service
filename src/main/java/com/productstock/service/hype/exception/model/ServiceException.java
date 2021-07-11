package com.productstock.service.hype.exception.model;

import com.productstock.service.hype.exception.base.BaseException;

public class ServiceException extends BaseException {

  public ServiceException(String code, Throwable throwable, Object... parameters) {
    super(code, throwable, parameters);
  }
}
