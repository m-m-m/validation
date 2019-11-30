/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.localdatetime;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import io.github.mmm.validation.temporal.ValidatorTemporalBefore;

/**
 * Implementation of {@link ValidatorTemporalBefore} for {@link LocalDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalDateTimeBefore extends ValidatorTemporalBefore<LocalDateTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorLocalDateTimeBefore(Supplier<LocalDateTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorLocalDateTimeBefore(LocalDateTime value) {

    super(value);
  }

  @Override
  protected boolean isBefore(LocalDateTime value, LocalDateTime limit) {

    return value.isBefore(limit);
  }

}
