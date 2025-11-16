/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import io.github.mmm.base.i18n.Localizable;

/**
 * {@link ValidationResult} that is {@link #isValid() valid}.
 *
 * @since 1.0.0
 */
public final class ValidationResultValid extends AtomicValidationResult {

  private static final String VALID = "Valid";

  private static final ValidationResultValid INSTANCE = new ValidationResultValid();

  private ValidationResultValid() {

    super(null, Localizable.ofStatic(VALID), null);
  }

  @Override
  public boolean isValid() {

    return true;
  }

  /**
   * @return the singleton instance of this class.
   */
  public static ValidationResultValid get() {

    return INSTANCE;
  }

}
