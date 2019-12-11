/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

/**
 * Abstract base implementation of {@link ValidationResult}.
 *
 * @since 1.0.0
 */
public abstract class AbstractValidationResult implements ValidationResult {

  private final String source;

  private final String code;

  /**
   * The constructor.
   *
   * @param code the {@link #getCode() code}.
   * @param source the optional {@link #getSource() source}.
   */
  public AbstractValidationResult(String code, String source) {

    super();
    this.source = source;
    this.code = code;
  }

  @Override
  public final boolean isValid() {

    return false;
  }

  @Override
  public String getSource() {

    return this.source;
  }

  @Override
  public String getCode() {

    return this.code;
  }

  @Override
  public String toString() {

    return this.code + ": " + getMessage();
  }

}
