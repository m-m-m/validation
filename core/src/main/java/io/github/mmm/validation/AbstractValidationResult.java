/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.io.IOException;

import io.github.mmm.base.exception.RuntimeIoException;

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

  /**
   * @param buffer the {@link Appendable} where to {@link Appendable#append(CharSequence) append} the {@link #getCode()
   *        code}.
   * @param space to append an extra space.
   */
  protected void appendCode(Appendable buffer, boolean space) {

    try {
      buffer.append('[');
      buffer.append(this.code);
      buffer.append(']');
      if (space) {
        buffer.append(' ');
      }
    } catch (IOException e) {
      throw new RuntimeIoException(e);
    }
  }

  @Override
  public String toString() {

    return getMessage(true);
  }

}
