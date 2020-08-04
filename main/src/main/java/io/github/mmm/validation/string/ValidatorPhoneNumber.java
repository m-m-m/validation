/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import java.util.regex.Pattern;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} is a valid email address.
 *
 * @since 1.0.0
 */
public final class ValidatorPhoneNumber extends AbstractValueValidator<CharSequence> {

  /** @see #get() */
  private static final ValidatorPhoneNumber INSTANCE = new ValidatorPhoneNumber();

  private static final Pattern PATTERN = Pattern.compile("^([+][0-9]{1,4}[ ]*(\\(0\\))?)?[-\\s/0-9]*$");

  /** @see #getId() */
  public static final String ID = "PhoneNumber";

  /**
   * The constructor.
   */
  private ValidatorPhoneNumber() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    if (isValidPhoneNumber(value.toString().trim())) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorNoPhoneNumber(value);
  }

  static boolean isValidPhoneNumber(String value) {

    return PATTERN.matcher(value).matches();
  }

  /**
   * @return the instance of {@link ValidatorPhoneNumber}.
   */
  public static ValidatorPhoneNumber get() {

    return INSTANCE;
  }

}
