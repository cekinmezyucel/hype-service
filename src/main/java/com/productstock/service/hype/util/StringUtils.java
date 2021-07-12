package com.productstock.service.hype.util;

import com.productstock.service.hype.model.Action;
import java.util.Arrays;

public class StringUtils {

  private StringUtils() {
    super();
  }

  /**
   * <p>
   * Checks if a CharSequence is empty ("") or null.
   * </p>
   *
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("bob")     = false
   * StringUtils.isEmpty("  bob  ") = false
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is empty or null
   */
  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  public static Action getAction(String message) {
    return Action.valueOf(Arrays.stream(message.split(";")).findFirst().get());
  }

  public static String getValue(String message) {
    return message.split(";")[1];
  }

}
