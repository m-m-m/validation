/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link io.github.mmm.validation.Validator#validate(Object, Object)
 * validating} that a {@link java.time.temporal.Temporal} lies in the future.
 *
 * @param <V> is the generic type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public abstract class ValidatorTemporalFuture<V> extends AbstractValueValidator<V> {

  /** @see #getCode() */
  public static final String CODE = "Future";

  /**
   * The constructor.
   */
  public ValidatorTemporalFuture() {

    super();
  }

  @Override
  protected String getCode() {

    return CODE;
  }

  @Override
  protected NlsMessage validateNotNull(V value) {

    if (isFuture(value)) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorValueNotInFuture(value);
  }

  /**
   * @param value the date to check.
   * @return {@code true} if in future, {@code false} otherwise.
   */
  protected abstract boolean isFuture(V value);

}
