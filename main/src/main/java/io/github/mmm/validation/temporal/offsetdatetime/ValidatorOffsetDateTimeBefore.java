/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.offsetdatetime;

import java.time.OffsetDateTime;
import java.util.function.Supplier;

import io.github.mmm.validation.temporal.ValidatorTemporalBefore;

/**
 * Implementation of {@link ValidatorTemporalBefore} for {@link OffsetDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorOffsetDateTimeBefore extends ValidatorTemporalBefore<OffsetDateTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorOffsetDateTimeBefore(Supplier<OffsetDateTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorOffsetDateTimeBefore(OffsetDateTime value) {

    super(value);
  }

  @Override
  protected boolean isBefore(OffsetDateTime value, OffsetDateTime limit) {

    return value.isBefore(limit);
  }

}
