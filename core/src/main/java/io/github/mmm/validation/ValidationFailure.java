/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.Locale;
import java.util.Objects;

import io.github.mmm.base.i18n.Localizable;

/**
 * Implementation of {@link ValidationResult} for a failure without {@link #getLocalizedMessage(Locale) I18N support}.
 *
 * @since 1.0.0
 */
public class ValidationFailure extends AbstractValidationResult {

  private final Localizable message;

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

    super(code, source);
    Objects.requireNonNull(message, "message");
    this.message = message;
  }

  @Override
  public int getChildCount() {

    return 0;
  }

  @Override
  public ValidationResult getChild(int index) {

    return null;
  }

  @Override
  public String getMessage() {

    return this.message.getMessage();
  }

  @Override
  public String getLocalizedMessage() {

    return this.message.getLocalizedMessage();
  }

  @Override
  public String getLocalizedMessage(Locale locale) {

    return this.message.getLocalizedMessage(locale);
  }

  @Override
  public void getLocalizedMessage(Locale locale, Appendable buffer, boolean verbose) {

    if (verbose) {
      appendCode(buffer, true);
    }
    this.message.getLocalizedMessage(locale, buffer);
  }

  @Override
  public ValidationResult add(ValidationResult result) {

    if ((result == null) || result.isValid()) {
      return this;
    } else if (result instanceof ComposedValidationFailure) {
      return result.add(this);
    }
    return new ComposedValidationFailure(getSource(), this, result);
  }

}
