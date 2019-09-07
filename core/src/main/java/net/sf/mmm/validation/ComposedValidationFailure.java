/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.validation;

import java.util.Locale;

/**
 * {@link ValidationResult} composing multiple {@link ValidationResult#isValid() invalid} {@link ValidationResult}s.
 *
 * @since 1.0.0
 */
public class ComposedValidationFailure extends AbstractValidationResult {

  /** @see #getCode() */
  public static final String CODE = ComposedValidator.CODE;

  private ValidationResult[] failures;

  /**
   * The constructor.
   *
   * @param source the optional {@link #getSource() source}. May be {@code null}.
   * @param failures the {@link ValidationResult#isValid() invalid} {@link ValidationResult}s to compose.
   */
  public ComposedValidationFailure(String source, ValidationResult... failures) {

    this(CODE, source, failures);
  }

  /**
   * The constructor.
   *
   * @param code is the {@link #getCode() code}.
   * @param source the optional {@link #getSource() source}. May be {@code null}.
   * @param failures the {@link ValidationResult#isValid() invalid} {@link ValidationResult}s to compose.
   */
  public ComposedValidationFailure(String code, String source, ValidationResult... failures) {

    super(code, source);
    assert (onlyFailures(failures));
    this.failures = failures;
  }

  private static boolean onlyFailures(ValidationResult[] failures) {

    for (ValidationResult failure : failures) {
      if (failure.isValid()) {
        return false;
      }
    }
    return true;
  }

  /**
   * @return the line separator.
   */
  protected String getSeparator() {

    return "\n";
  }

  @Override
  public int getChildCount() {

    return this.failures.length;
  }

  @Override
  public ValidationResult getChild(int index) {

    return this.failures[index];
  }

  @Override
  public String getMessage(Locale locale) {

    StringBuilder buffer = new StringBuilder();
    for (ValidationResult failure : this.failures) {
      if (buffer.length() > 0) {
        buffer.append(getSeparator());
      }
      String message;
      if (locale == null) {
        message = failure.getMessage();
      } else {
        message = failure.getMessage(locale);
      }
      buffer.append(message);
    }
    return buffer.toString();
  }

}
