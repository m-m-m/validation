/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import io.github.mmm.base.filter.CharFilter;
import io.github.mmm.base.filter.ListCharFilter;
import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} is a valid email address.
 *
 * @since 1.0.0
 */
public final class ValidatorHostName extends AbstractValueValidator<CharSequence> {

  /** @see #get() */
  private static final ValidatorHostName INSTANCE = new ValidatorHostName();

  // RFC-5892 Sec. 2.3, etc. even though characters such as hash ('#') are not explicitly forbidden, they are still not
  // allowed as part
  static final CharFilter EXTRA_ASCII_FILTER = new ListCharFilter('!', '"', '$', '\'', '(', ')', '*', ',', '-', '@',
      '[', '\\', ']', '_', '`', '{', '}');

  // static final CharFilter SEGMENT_FILTER = CharFilter.LATIN_DIGIT_OR_LETTER_FILTER
  // .compose(new ListCharFilter(charArray));

  /** @see #getId() */
  public static final String ID = "HostName";

  private ValidatorHostName() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    if (isValidHostName(value.toString().trim())) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorNoHostName(value);
  }

  static boolean isValidHostName(String value) {

    String[] segments = StringHelper.splitHost(value);
    if (!isValidHostName(segments)) {
      return false;
    }
    return true;
  }

  static boolean isValidHostName(String[] segments) {

    if ((segments == null) || (segments.length == 0)) {
      return false;
    }
    IdnEncoder encoder = new IdnEncoder();
    int length = segments.length - 1; // dots
    int i = 0;
    String segment = null;
    while (i < segments.length) {
      segment = segments[i++];
      if (!isValidSegment(segment)) {
        String ascii = segment.replace("ß", "ss"); // proper stringprep (RFC 3454)?
        ascii = encoder.encode(ascii);
        if (segment.equals(ascii) || !isValidSegment(ascii)) {
          return false;
        }
      }
      length = length + segment.length();
    }
    if ((length < 2) || (length >= 253)) {
      return false;
    }
    if (!isValidTld(segment)) {
      return false;
    }
    return true;
  }

  private static boolean isValidTld(String segment) {

    // actually length must be 2+ ?!?
    char first = segment.charAt(0);
    if (!CharFilter.LATIN_LETTER.accept(first)) {
      return false; // first char of tld has to be letter.
    }
    return true;
  }

  private static boolean isValidSegment(String segment) {

    if (segment == null) {
      return false;
    }
    int length = segment.length();
    if ((length < 1) || (length > 63)) {
      return false;
    }
    int last = length - 1;
    for (int i = 0; i < length; i++) {
      char c = segment.charAt(i);
      if (!CharFilter.LATIN_LETTER_OR_DIGIT.accept(c)) {
        if (i == 0) {
          return false; // must start with letter or digit (IDN segment starts with "xn--")
        }
        if (c == '-') {
          if (i == last) {
            return false; // hypen may not be first or last char
          }
        } else if (!EXTRA_ASCII_FILTER.accept(c)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * @return the instance of {@link ValidatorHostName}.
   */
  public static ValidatorHostName get() {

    return INSTANCE;
  }
}
