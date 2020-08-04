/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import io.github.mmm.base.exception.RuntimeIoException;

/**
 * {@link ValidationResult} composing multiple {@link ValidationResult#isValid() invalid} {@link ValidationResult}s.
 *
 * @since 1.0.0
 */
public class ComposedValidationFailure extends AbstractValidationResult {

  /** @see #getCode() */
  public static final String CODE = ComposedValidator.ID;

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
  public void getLocalizedMessage(Locale locale, Appendable buffer) {

    try {
      String separator = null;
      for (ValidationResult failure : this.failures) {
        if (separator == null) {
          separator = getSeparator();
        } else {
          buffer.append(separator);
        }
        String message = failure.getLocalizedMessage(locale);
        buffer.append(message);
      }
    } catch (IOException e) {
      throw new RuntimeIoException(e);
    }
  }

  @Override
  public ValidationResult add(ValidationResult result) {

    if ((result == null) || result.isValid()) {
      return this;
    }
    ValidationResult[] composedFailures;
    if (result instanceof ComposedValidationFailure) {
      ValidationResult[] otherFailures = ((ComposedValidationFailure) result).failures;
      composedFailures = Arrays.copyOf(this.failures, this.failures.length + otherFailures.length);
      System.arraycopy(otherFailures, 0, composedFailures, this.failures.length, otherFailures.length);
    } else {
      composedFailures = Arrays.copyOf(this.failures, this.failures.length + 1);
      composedFailures[this.failures.length] = result;
    }
    return new ComposedValidationFailure(getSource(), composedFailures);
  }

}
