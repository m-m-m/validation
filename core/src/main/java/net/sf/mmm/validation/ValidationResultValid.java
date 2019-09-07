/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.validation;

import java.util.Locale;

/**
 * {@link ValidationResult} that is {@link #isValid() valid}.
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
  public String getMessage(Locale locale) {

    return null;
  }

  @Override
  public String getCode() {

    return null;
  }

  /**
   * @return the singleton instance of this class.
   */
  public static ValidationResultValid getInstance() {

    return INSTANCE;
  }

}
