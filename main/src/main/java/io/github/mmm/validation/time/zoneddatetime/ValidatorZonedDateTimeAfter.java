/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.zoneddatetime;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorTemporalAfter;

/**
 * Implementation of {@link ValidatorTemporalAfter} for {@link ZonedDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorZonedDateTimeAfter extends ValidatorTemporalAfter<ZonedDateTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorZonedDateTimeAfter(Supplier<ZonedDateTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorZonedDateTimeAfter(ZonedDateTime value) {

    super(value);
  }

  @Override
  protected boolean isAfter(ZonedDateTime value, ZonedDateTime limit) {

    return value.isAfter(limit);
  }

}
