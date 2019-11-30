/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.offsettime;

import java.time.OffsetTime;
import java.util.function.Supplier;

import io.github.mmm.validation.temporal.ValidatorTemporalAfter;

/**
 * Implementation of {@link ValidatorTemporalAfter} for {@link OffsetTime}.
 *
 * @since 1.0.0
 */
public class ValidatorOffsetTimeAfter extends ValidatorTemporalAfter<OffsetTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorOffsetTimeAfter(Supplier<OffsetTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorOffsetTimeAfter(OffsetTime value) {

    super(value);
  }

  @Override
  protected boolean isAfter(OffsetTime value, OffsetTime limit) {

    return value.isAfter(limit);
  }

}
