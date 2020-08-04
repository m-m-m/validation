/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link io.github.mmm.validation.Validator#validate(Object, Object)
 * validating} that a {@link java.time.temporal.Temporal} lies in the past.
 *
 * @param <V> is the generic type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public abstract class ValidatorTemporalPast<V> extends AbstractValueValidator<V> {

  /** @see #getId() */
  public static final String ID = "Past";

  /**
   * The constructor.
   */
  public ValidatorTemporalPast() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(V value) {

    if (isPast(value)) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorValueNotInPast(value);
  }

  /**
   * @param value the date to check.
   * @return {@code true} if in future, {@code false} otherwise.
   */
  protected abstract boolean isPast(V value);

}
