/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.instant;

import java.time.Instant;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorTemporalBefore;

/**
 * Implementation of {@link ValidatorTemporalBefore} for {@link Instant}.
 *
 * @since 1.0.0
 */
public class ValidatorInstantBefore extends ValidatorTemporalBefore<Instant> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorInstantBefore(Supplier<Instant> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorInstantBefore(Instant value) {

    super(value);
  }

  @Override
  protected boolean isBefore(Instant value, Instant limit) {

    return value.isBefore(limit);
  }

}
