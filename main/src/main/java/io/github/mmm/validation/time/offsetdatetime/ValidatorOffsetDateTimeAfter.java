/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.offsetdatetime;

import java.time.OffsetDateTime;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorTemporalAfter;

/**
 * Implementation of {@link ValidatorTemporalAfter} for {@link OffsetDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorOffsetDateTimeAfter extends ValidatorTemporalAfter<OffsetDateTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorOffsetDateTimeAfter(Supplier<OffsetDateTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorOffsetDateTimeAfter(OffsetDateTime value) {

    super(value);
  }

  @Override
  protected boolean isAfter(OffsetDateTime value, OffsetDateTime limit) {

    return value.isAfter(limit);
  }

}
