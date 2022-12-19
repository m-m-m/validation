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

  private final ValidationResult[] failures;

  private final boolean appendSources;

  /**
   * The constructor.
   *
   * @param source the optional {@link #getSource() source}. May be {@code null}.
   * @param failures the {@link ValidationResult#isValid() invalid} {@link ValidationResult}s to compose.
   */
  public ComposedValidationFailure(String source, ValidationResult... failures) {

    this(CODE, source, false, failures);
  }

  /**
   * The constructor.
   *
   * @param source the optional {@link #getSource() source}. May be {@code null}.
   * @param appendSources the {@link #isAppendSources() append sources flag}.
   * @param failures the {@link ValidationResult#isValid() invalid} {@link ValidationResult}s to compose.
   */
  public ComposedValidationFailure(String source, boolean appendSources, ValidationResult... failures) {

    this(CODE, source, appendSources, failures);
  }

  /**
   * The constructor.
   *
   * @param code the {@link #getCode() code}.
   * @param appendSources the {@link #isAppendSources() append sources flag}.
   * @param source the optional {@link #getSource() source}. May be {@code null}.
   * @param failures the {@link ValidationResult#isValid() invalid} {@link ValidationResult}s to compose.
   */
  public ComposedValidationFailure(String code, String source, boolean appendSources, ValidationResult... failures) {

    super(code, source);
    assert (onlyFailures(failures));
    this.appendSources = appendSources;
    this.failures = failures;
  }

  /**
   * @return {@code true} to append the {@link #getSource() source}s of the {@link ValidationFailure}s to the
   *         {@link #getMessage() message}, {@code false} otherwise.
   */
  public boolean isAppendSources() {

    return this.appendSources;
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

    if ((index >= 0) && (index < this.failures.length)) {
      return this.failures[index];
    }
    return null;
  }

  @Override
  public void getLocalizedMessage(Locale locale, Appendable buffer, boolean verbose) {

    getLocalizedMessage("", locale, buffer, verbose, this.appendSources);
  }

  /**
   * @see #getLocalizedMessage(Locale, Appendable)
   *
   * @param indent the current indentation.
   * @param locale the {@link Locale} to translate to.
   * @param buffer the {@link Appendable} where to {@link Appendable#append(CharSequence) write} the message to.
   * @param verbose the verbose flag (to include {@link #getCode() code}(s), etc.
   * @param appendSrc the {@link #isAppendSources() append sources flag}.
   */
  protected void getLocalizedMessage(String indent, Locale locale, Appendable buffer, boolean verbose,
      boolean appendSrc) {

    try {
      String separator = null;
      boolean appended = appendSource(indent, appendSrc, this, buffer);
      if (verbose) {
        if (!appended) {
          buffer.append(indent);
          appended = true;
        }
        appendCode(buffer, false);
      }
      if (appended) {
        separator = getSeparator();
      }
      indent = indent + "  ";
      for (ValidationResult failure : this.failures) {
        if (separator == null) {
          separator = getSeparator();
        } else {
          buffer.append(separator);
        }
        if (failure instanceof ComposedValidationFailure) {
          ((ComposedValidationFailure) failure).getLocalizedMessage(indent, locale, buffer, verbose, appendSrc);
        } else {
          appendSource(indent, appendSrc, failure, buffer);
          failure.getLocalizedMessage(locale, buffer, verbose);
        }
      }
    } catch (IOException e) {
      throw new RuntimeIoException(e);
    }
  }

  private boolean appendSource(String indent, boolean appendSrc, ValidationResult result, Appendable buffer)
      throws IOException {

    if (appendSrc && (result != null) && !result.isValid()) {
      String source = result.getSource();
      if ((source != null) && !source.isEmpty()) {
        buffer.append(indent);
        buffer.append(source);
        buffer.append(": ");
        return true;
      }
    }
    return false;
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

  @Override
  public boolean containsCode(String code) {

    if (super.containsCode(code)) {
      return true;
    }
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      ValidationResult child = getChild(i);
      if (child.containsCode(code)) {
        return true;
      }
    }
    return false;
  }

}
