/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.impl;

import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.ComposedValidator;
import io.github.mmm.validation.ValidationResult;
import io.github.mmm.validation.ValidationResultValid;
import io.github.mmm.validation.Validator;

/**
 * {@link Validator} that always validates successfully.
 *
 * @see Validator#none()
 * @since 1.0.0
 */
public final class ValidatorNone extends AbstractValidator<Object> {

  /** @see Validator#none() */
  public static final ValidatorNone INSTANCE = new ValidatorNone();

  /**
   * The constructor.
   */
  public ValidatorNone() {

    super();
  }

  @Override
  public ValidationResult validate(Object value, Object valueSource) {

    return ValidationResultValid.get();
  }

  @SuppressWarnings("unchecked")
  @Override
  public Validator<Object> append(Validator<? super Object>... validators) {

    return new ComposedValidator<>(validators);
  }

  @Override
  public int hashCode() {

    return 0;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    return true;
  }

}
