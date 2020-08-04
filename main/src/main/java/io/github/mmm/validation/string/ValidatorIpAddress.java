/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} is a valid IP address.
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
      return ValidatorIpV6Address.isValidIpV6Address(value);
    }
    return ValidatorIpV4Address.isValidIpV4Address(value);
  }

  /**
   * @return the instance of {@link ValidatorIpAddress}.
   */
  public static ValidatorIpAddress get() {

    return INSTANCE;
  }
}
