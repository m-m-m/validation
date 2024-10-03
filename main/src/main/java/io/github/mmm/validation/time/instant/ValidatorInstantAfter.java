/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.instant;

import java.time.Instant;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorTemporalAfter;

/**
 * Implementation of {@link ValidatorTemporalAfter} for {@link Instant}.
 *
 * @since 1.0.0
 */
public class ValidatorInstantAfter extends ValidatorTemporalAfter<Instant> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorInstantAfter(Supplier<Instant> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorInstantAfter(Instant value) {

    super(value);
  }

  @Override
  protected boolean isAfter(Instant value, Instant limit) {

    return value.isAfter(limit);
  }

}
