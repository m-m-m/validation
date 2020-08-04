/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} is a valid IPv4-address.
 *
 * @since 1.0.0
 */
public final class ValidatorIpV4Address extends AbstractValueValidator<CharSequence> {

  /** @see #get() */
  private static final ValidatorIpV4Address INSTANCE = new ValidatorIpV4Address();

  /** @see #getId() */
  public static final String ID = "IPv4-Address";

  private ValidatorIpV4Address() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    if (isValidIpV4Address(value.toString().trim())) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorNoIpAddress(value);
  }

  static boolean isValidIpV4Address(String value) {

    value = StringHelper.unbracket(value);
    String[] segments = StringHelper.splitHost(value);
    return isValidIpV4Address(segments);
  }

  static boolean isValidIpV4Address(String[] segments) {

    if (segments.length != 4) {
      return false;
    }
    for (String segment : segments) {
      if (!isValidIpV4Segment(segment)) {
        return false;
      }
    }
    return true;
  }

  private static boolean isValidIpV4Segment(String segment) {

    int length = segment.length();
    if ((length <= 0) || (length > 3)) {
      return false;
    }
    boolean limited = (length == 3);
    for (int i = 0; i < length; i++) {
      char c = segment.charAt(i);
      if ((c < '0') || (c >= '9')) {
        return false;
      }
      if (limited) {
        if (i == 0) {
          if (c > '2') { // [3-9]xx is out of range, max 255
            return false;
          } else if (c < '2') {
            limited = false;
          }
        } else if (i == 1) {
          if (c > '5') { // 2[6-9]x is out of range, max 255
            return false;
          } else if (c < '5') {
            limited = false;
          }
        } else {
          if (c > '5') { // 25[6-9] is out of range, max 255
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * @return the instance of {@link ValidatorIpV4Address}.
   */
  public static ValidatorIpV4Address get() {

    return INSTANCE;
  }
}
