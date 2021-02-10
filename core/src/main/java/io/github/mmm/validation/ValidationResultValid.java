/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.Locale;

/**
 * {@link ValidationResult} that is {@link #isValid() valid}.
 *
 * @since 1.0.0
 */
public final class ValidationResultValid implements ValidationResult {

  private static final ValidationResultValid INSTANCE = new ValidationResultValid();

  private ValidationResultValid() {

    super();
  }

  @Override
  public boolean isValid() {

    return true;
  }

  @Override
  public String getSource() {

    return null;
  }

  @Override
  public String getMessage() {

    return null;
  }

  @Override
  public String getLocalizedMessage() {

    return null;
  }

  @Override
  public String getLocalizedMessage(Locale locale) {

    return null;
  }

  @Override
  public void getLocalizedMessage(Locale locale, Appendable buffer, boolean verbose) {

  }

  @Override
  public String getCode() {

    return null;
  }

  @Override
  public ValidationResult add(ValidationResult result) {

    return result;
  }

  @Override
  public String toString() {

    return "Valid";
  }

  /**
   * @return the singleton instance of this class.
   */
  public static ValidationResultValid get() {

    return INSTANCE;
  }

}
