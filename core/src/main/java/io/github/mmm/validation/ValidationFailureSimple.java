/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.Locale;

/**
 * Implementation of {@link ValidationResult} for a failure without {@link #getMessage(Locale) I18N support}.
 *
 * @since 1.0.0
 */
public class ValidationFailureSimple extends AbstractValidationResult {

  private final String message;

  /**
   * The constructor.
   *
   * @param code the {@link #getCode() code}.
   * @param message the {@link #getMessage() message}.
   */
  public ValidationFailureSimple(String code, String message) {

    this(code, message, null);
  }

  /**
   * The constructor.
   *
   * @param code the {@link #getCode() code}.
   * @param message the {@link #getMessage() message}.
   * @param source the optional {@link #getSource() source}.
   */
  public ValidationFailureSimple(String code, String message, String source) {

    super(code, source);
    this.message = message;
  }

  @Override
  public String getMessage(Locale locale) {

    return this.message;
  }

}
