package com.productstock.service.hype.util;

import static java.util.Objects.isNull;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BundleUtils {

  private static final Logger log = LoggerFactory.getLogger(BundleUtils.class);

  private BundleUtils() {
    super();
  }

  public static String getErrorMessageFromBundle(String code, Object... parameters) {
    return getFromStumpyBundle("error." + code.toUpperCase() + ".text", parameters);
  }

  public static String getHttpStatusFromBundle(String code, Object... parameters) {
    return getFromStumpyBundle("error." + code.toUpperCase() + ".httpstatus", parameters);
  }

  private static String getFromStumpyBundle(String messageKey, Object... params) {
    return getFrom("com.productstock.service.hype.i18n.exception-bundle", messageKey, params);
  }

  private static String getFrom(String bundleFullName, String messageKey, Object... params) {
    ClassLoader loader = getClassLoader();

    if (messageKey.indexOf('.') == -1) {
      return messageKey;
    }

    // Default Locale is US.
    Locale locale = Locale.US;

    ResourceBundle bundle = ResourceBundle.getBundle(bundleFullName, locale, loader);

    String fromBundle = "";

    try {
      fromBundle = bundle.getString(messageKey);
    } catch (MissingResourceException exception) {
      fromBundle = messageKey;
    }

    if (isNull(params) || params.length == 0) {
      return fromBundle;
    }

    MessageFormat formatter = new MessageFormat(fromBundle, locale);
    try {
      return formatter.format(params);
    } catch (Exception exception) {
      log.error("Could not format " + messageKey, exception);
    }
    return messageKey;
  }

  private static ClassLoader getClassLoader() {
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    if (loader == null) {
      loader = ClassLoader.getSystemClassLoader();
    }
    return loader;
  }

}
