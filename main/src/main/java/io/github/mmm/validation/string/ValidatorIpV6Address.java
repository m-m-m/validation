/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} is a valid IPv6 address.
 *
 * @since 1.0.0
 */
public final class ValidatorIpV6Address extends AbstractValueValidator<CharSequence> {

  /** @see #get() */
  private static final ValidatorIpV6Address INSTANCE = new ValidatorIpV6Address();

  /** @see #getId() */
  public static final String ID = "IPv6-Address";

  private ValidatorIpV6Address() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    if (isValidIpV6Address(value.toString().trim())) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorNoIpAddress(value);
  }

  static boolean isValidIpV6Address(String value) {

    value = StringHelper.unbracket(value);
    int length = value.length();
    if (length == 0) {
      return false;
    }
    int percentIndex = value.indexOf('%');
    if (percentIndex > 0) {
      String zone = value.substring(percentIndex + 1);
      if (!isValidZone(zone)) {
        return false;
      }
      value = value.substring(0, percentIndex);
      length = percentIndex;
    }
    int groupCount = 0; // number of groups/segments
    int colonCount = 0; // number of subsequent colons (1=":", 2="::")
    int digitCount = 0; // number of hex digits in current group/segment
    int i = 0; // char index
    int doubleColonCount = 0; // number of double colons ("::") passed.
    while (i < length) {
      char c = value.charAt(i++);
      if (c == ':') {
        if (digitCount > 0) {
          groupCount++;
          digitCount = 0;
        }
        colonCount++;
        if (colonCount == 2) {
          doubleColonCount++;
        } else if (colonCount > 2) {
          return false;
        }
      } else if (CharFilter.HEX_DIGIT_FILTER.accept(c)) {
        colonCount = 0;
        digitCount++;
        if (digitCount > 4) {
          return false; // group/segment cannot have more than 4 hex digits
        }
      } else {
        return false; // illegal char
      }
    }
    if (colonCount == 1) {
      return false; // may nit end on single colon
    }
    if (digitCount > 0) {
      groupCount++; // count last group/segment
    }
    if ((groupCount + doubleColonCount) > 8) {
      return false;
    } else if ((groupCount < 8) && (doubleColonCount == 0)) {
      return false;
    }
    return true;
  }

  private static boolean isValidZone(String zoneIndex) {

    return true;
  }

  /**
   * @return the instance of {@link ValidatorIpV6Address}.
   */
  public static ValidatorIpV6Address get() {

    return INSTANCE;
  }
}
