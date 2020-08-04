/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} is a valid email address (RFC822).
 *
 * @since 1.0.0
 */
public final class ValidatorEmail extends AbstractValueValidator<CharSequence> {

  /** @see #get() */
  private static final ValidatorEmail INSTANCE = new ValidatorEmail();

  /** @see #getId() */
  public static final String ID = "Email";

  private ValidatorEmail() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    if (isValidEmail(value.toString().trim())) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorNoEmail(value);
  }

  static boolean isValidEmail(String email) {

    int length = email.length();
    if (length == 0) {
      return false;
    }
    char lastChar = email.charAt(length - 1);
    if (lastChar == '.') {
      return false;
    }
    String displayName = null;
    String emailSpec;
    if (lastChar == '>') {
      int ltStart = 0;
      if (email.charAt(0) == '"') {
        ltStart = StringHelper.indexOfUnescaped(email, '"', 1, length - 4);
        if (ltStart < 0) {
          return false;
        }
      }
      int ltIndex = email.indexOf('<', ltStart);
      if (ltIndex < 0) {
        return false;
      }
      displayName = email.substring(0, ltIndex).trim();
      emailSpec = email.substring(ltIndex + 1, length - 1);
    } else {
      emailSpec = email;
    }
    if (!ValidatorEmailSpec.isValidEmailSpec(emailSpec)) {
      return false;
    }
    if ((displayName != null) && !isValidDisplayName(displayName)) {
      return false;
    }
    return true;
  }

  static boolean isValidDisplayName(String displayName) {

    if (displayName.startsWith("\"")) {
      int quoteEndIndex = StringHelper.indexOfUnescaped(displayName, '"', 1);
      if (quoteEndIndex != (displayName.length() - 1)) {
        return false;
      }
    } else {
      for (int i = 0; i < displayName.length(); i++) {
        if (!ValidatorEmailSpec.LOCAL_PART_FILTER.accept(displayName.charAt(i))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * @return the instance of {@link ValidatorEmail}.
   */
  public static ValidatorEmail get() {

    return INSTANCE;
  }

}
