/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} is a valid host or domain. Therefore the value must be a valid {@link ValidatorHostName
 * hostname} or {@link ValidatorIpAddress IP-address}.
 *
 * @since 1.0.0
 */
public final class ValidatorHost extends AbstractValueValidator<CharSequence> {

  /** @see #get() */
  private static final ValidatorHost INSTANCE = new ValidatorHost();

  /** @see #getId() */
  public static final String ID = "Host";

  private ValidatorHost() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    if (isValidHost(value.toString().trim())) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorNoHost(value);
  }

  static boolean isValidHost(String value) {

    int length = value.length();
    if ((length < 2) || (length >= 253)) {
      return false; // quick fail, detailed IDN check in ValidatorHostName (see below)
    }
    if (ValidatorIpAddress.isValidIpAddress(value)) {
      return true;
    } else if (ValidatorHostName.isValidHostName(value)) {
      return true;
    }
    return false;
  }

  /**
   * @return the instance of {@link ValidatorHost}.
   */
  public static ValidatorHost get() {

    return INSTANCE;
  }

}
