/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.Locale;

import io.github.mmm.base.i18n.Localizable;

/**
 * Implementation of {@link ValidationResult} for a failure without {@link #getLocalizedMessage(Locale) I18N support}.
 *
 * @since 1.0.0
 */
public final class ValidationFailure extends AtomicValidationResult {

  /**
   * The constructor.
   *
   * @param code the {@link #getCode() code}.
   * @param message the {@link #getMessage() message}.
   */
  public ValidationFailure(String code, Localizable message) {

    this(code, message, null);
  }

  /**
   * The constructor.
   *
   * @param code the {@link #getCode() code}.
   * @param message the {@link #getMessage() message}.
   * @param source the optional {@link #getSource() source}.
   */
  public ValidationFailure(String code, Localizable message, String source) {

    super(code, message, source);
  }

  @Override
  public final boolean isValid() {

    return false;
  }

}
