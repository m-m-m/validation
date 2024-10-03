/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.offsettime;

import java.time.OffsetTime;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorTemporalBefore;

/**
 * Implementation of {@link ValidatorTemporalBefore} for {@link OffsetTime}.
 *
 * @since 1.0.0
 */
public class ValidatorOffsetTimeBefore extends ValidatorTemporalBefore<OffsetTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorOffsetTimeBefore(Supplier<OffsetTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorOffsetTimeBefore(OffsetTime value) {

    super(value);
  }

  @Override
  protected boolean isBefore(OffsetTime value, OffsetTime limit) {

    return value.isBefore(limit);
  }

}
