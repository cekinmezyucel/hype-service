package com.productstock.service.hype.exception.model;

import com.productstock.service.hype.exception.base.BaseException;

public class BusinessException extends BaseException {

  public BusinessException(String code, Object... parameters) {
    super(code, parameters);
  }
}
