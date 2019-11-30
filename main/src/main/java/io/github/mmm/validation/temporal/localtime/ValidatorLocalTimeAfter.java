/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.localtime;

import java.time.LocalTime;
import java.util.function.Supplier;

import io.github.mmm.validation.temporal.ValidatorTemporalAfter;

/**
 * Implementation of {@link ValidatorTemporalAfter} for {@link LocalTime}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalTimeAfter extends ValidatorTemporalAfter<LocalTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorLocalTimeAfter(Supplier<LocalTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorLocalTimeAfter(LocalTime value) {

    super(value);
  }

  @Override
  protected boolean isAfter(LocalTime value, LocalTime limit) {

    return value.isAfter(limit);
  }

}
