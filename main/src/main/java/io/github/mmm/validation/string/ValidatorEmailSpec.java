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
 * {@link CharSequence} is a valid email address specification (a raw email without display name, comments, etc.).
 *
 * @since 1.0.0
 */
public final class ValidatorEmailSpec extends AbstractValueValidator<CharSequence> {

  /** @see #get() */
  private static final ValidatorEmailSpec INSTANCE = new ValidatorEmailSpec();

  static final CharFilter SPECIAL_CHAR_FILTER = c -> (c >= '\u0080') && (c <= '\uFFFF');

  static final ListCharFilter ATEXT_SEPCIAL_CHAR_FILTER = new ListCharFilter("!#$%&\'*+-/=?^_`{|}~");

  static final CharFilter ATEXT_FILTER = CharFilter.LATIN_LETTER_OR_DIGIT
      .compose(ATEXT_SEPCIAL_CHAR_FILTER.compose(SPECIAL_CHAR_FILTER));

  static final CharFilter LOCAL_PART_FILTER = ATEXT_FILTER.compose(SPECIAL_CHAR_FILTER);

  /** @see #getId() */
  public static final String ID = "EmailSpec";

  private ValidatorEmailSpec() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    if (isValidEmailSpec(value.toString().trim())) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorNoEmail(value);
  }

  static boolean isValidEmailSpec(String emailSpec) {

    int length = emailSpec.length();
    if (length == 0) {
      return false;
    }
    int atIndex = emailSpec.lastIndexOf('@');
    if (atIndex <= 0) {
      return false;
    }
    String localPart = emailSpec.substring(0, atIndex);
    if (!isValidLocalPart(localPart)) {
      return false;
    }
    String domain = emailSpec.substring(atIndex + 1);
    if (!isValidDomain(domain)) {
      return false;
    }
    return true;
  }

  static boolean isValidDomain(String domain) {

    if (StringHelper.hasBrackets(domain)) {
      return ValidatorIpAddress.isValidIpAddress(domain);
    } else {
      return ValidatorHostName.isValidHostName(domain);
    }
  }

  static boolean isValidLocalPart(String localPart) {

    localPart = localPart.trim();
    int length = localPart.length();
    if ((length == 0) || (length > 64)) {
      return false;
    }
    int lastIndex = length - 1;
    if ((length > 2) && (localPart.charAt(0) == '"') && (localPart.charAt(lastIndex) == '"')) { // quoted?
      boolean escape = false;
      for (int i = 1; i < lastIndex; i++) {
        char c = localPart.charAt(i);
        if (c == '\\') {
          escape = !escape;
        } else if (c == '"') {
          if (!escape) {
            return false; // un-escaped double-quote
          }
        } else {

        }
      }
    } else {
      int dotCount = 0;
      for (int i = 0; i < length; i++) {
        char c = localPart.charAt(i);
        if (c == '.') {
          if ((i == 0) || (i == lastIndex) || dotCount > 0) {
            return false;
          }
          dotCount++;
        } else if (LOCAL_PART_FILTER.accept(c)) {
          dotCount = 0;
        } else {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * @return the instance of {@link ValidatorEmailSpec}.
   */
  public static ValidatorEmailSpec get() {

    return INSTANCE;
  }

}
