package com.productstock.service.hype.exception.base;

import static java.lang.Integer.parseInt;
import static java.util.UUID.randomUUID;

import com.productstock.service.hype.util.BundleUtils;
import com.productstock.service.hype.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseException extends RuntimeException {

  private final String exceptionId;

  private final String code;

  private final int status;

  private final transient Object[] parameters;

  private final String errorMessage;

  protected BaseException(String code, Object... parameters) {
    super(String.format("%s, %s", code, BundleUtils.getErrorMessageFromBundle(code, parameters)));
    this.exceptionId = randomUUID().toString();
    this.code = code;
    this.parameters = parameters;
    this.errorMessage = BundleUtils.getErrorMessageFromBundle(code, parameters);
    this.status = parseInt(BundleUtils.getHttpStatusFromBundle(code, parameters));
  }

  protected BaseException(String code, Throwable throwable, Object... parameters) {
    super(throwable);
    this.exceptionId = randomUUID().toString();
    this.code = code;
    this.parameters = parameters;
    this.errorMessage = StringUtils.isEmpty(BundleUtils.getErrorMessageFromBundle(code, parameters)) ? throwable.getMessage()
        : BundleUtils.getErrorMessageFromBundle(code, parameters);
    this.status = parseInt(BundleUtils.getHttpStatusFromBundle(code, parameters));
  }

}
