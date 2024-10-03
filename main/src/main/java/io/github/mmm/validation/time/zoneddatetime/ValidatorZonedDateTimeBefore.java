/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.zoneddatetime;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorTemporalBefore;

/**
 * Implementation of {@link ValidatorTemporalBefore} for {@link ZonedDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorZonedDateTimeBefore extends ValidatorTemporalBefore<ZonedDateTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorZonedDateTimeBefore(Supplier<ZonedDateTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorZonedDateTimeBefore(ZonedDateTime value) {

    super(value);
  }

  @Override
  protected boolean isBefore(ZonedDateTime value, ZonedDateTime limit) {

    return value.isBefore(limit);
  }

}
