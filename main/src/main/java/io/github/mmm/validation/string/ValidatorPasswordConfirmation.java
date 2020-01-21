/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import io.github.mmm.base.i18n.Localizable;
import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} {@link Pattern#matcher(CharSequence) matches} a given {@link Pattern}.
 *
 * @since 1.0.0
 */
public class ValidatorPasswordConfirmation extends AbstractValueValidator<CharSequence> {

  /** @see #getCode() */
  public static final String CODE = "PwdConfirm";

  private final Supplier<? extends CharSequence> passwordSupplier;

  /**
   * The constructor.
   *
   * @param passwordSupplier the {@link Supplier} of the primary password that has to match.
   */
  public ValidatorPasswordConfirmation(Supplier<? extends CharSequence> passwordSupplier) {

    super();
    this.passwordSupplier = passwordSupplier;
  }

  @Override
  protected String getCode() {

    return CODE;
  }

  @Override
  protected Localizable validateNull() {

    return doValidate(null);
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    return doValidate(value);
  }

  private NlsMessage doValidate(CharSequence confirmationPassword) {

    CharSequence primaryPassword = this.passwordSupplier.get();
    if (!Objects.equals(confirmationPassword, primaryPassword)) {
      return NlsBundleValidation.INSTANCE.errorPasswordMismatch();
    }
    return null;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if (!super.equals(obj)) {
      return false;
    }
    ValidatorPasswordConfirmation other = (ValidatorPasswordConfirmation) obj;
    return this.passwordSupplier == other.passwordSupplier;
  }

}
