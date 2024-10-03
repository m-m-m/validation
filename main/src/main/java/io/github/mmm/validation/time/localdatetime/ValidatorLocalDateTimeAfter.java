/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.localdatetime;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorTemporalAfter;

/**
 * Implementation of {@link ValidatorTemporalAfter} for {@link LocalDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalDateTimeAfter extends ValidatorTemporalAfter<LocalDateTime> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorLocalDateTimeAfter(Supplier<LocalDateTime> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorLocalDateTimeAfter(LocalDateTime value) {

    super(value);
  }

  @Override
  protected boolean isAfter(LocalDateTime value, LocalDateTime limit) {

    return value.isAfter(limit);
  }

}
