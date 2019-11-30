/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.localtime;

import java.time.LocalTime;
import java.util.function.Supplier;

import io.github.mmm.validation.temporal.ValidatorTemporalBefore;

/**
 * Implementation of {@link ValidatorTemporalBefore} for {@link LocalTime}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalTimeBefore extends ValidatorTemporalBefore<LocalTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorLocalTimeBefore(Supplier<LocalTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorLocalTimeBefore(LocalTime value) {

    super(value);
  }

  @Override
  protected boolean isBefore(LocalTime value, LocalTime limit) {

    return value.isBefore(limit);
  }

}
