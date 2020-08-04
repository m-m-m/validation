/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} is a valid email address.
 *
 * @since 1.0.0
 */
public final class ValidatorIpAddress extends AbstractValueValidator<CharSequence> {

  /** @see #get() */
  private static final ValidatorIpAddress INSTANCE = new ValidatorIpAddress();

  /** @see #getId() */
  public static final String ID = "IP-Address";

  private ValidatorIpAddress() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    if (isValidIpAddress(value.toString().trim())) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorNoIpAddress(value);
  }

  static boolean isValidIpAddress(String value) {

    if (value.contains(":")) {
      return isValidIpV6Address(value);
    }
    value = StringHelper.unbracket(value);
    String[] segments = StringHelper.splitHost(value);
    return isValidIpV4Address(segments);
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
   * @return the instance of {@link ValidatorIpAddress}.
   */
  public static ValidatorIpAddress get() {

    return INSTANCE;
  }
}
