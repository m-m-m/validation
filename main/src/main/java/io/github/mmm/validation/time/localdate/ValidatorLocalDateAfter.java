/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.localdate;

import java.time.LocalDate;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorTemporalAfter;

/**
 * Implementation of {@link ValidatorTemporalAfter} for {@link LocalDate}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalDateAfter extends ValidatorTemporalAfter<LocalDate> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorLocalDateAfter(Supplier<LocalDate> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorLocalDateAfter(LocalDate value) {

    super(value);
  }

  @Override
  protected boolean isAfter(LocalDate value, LocalDate limit) {

    return value.isAfter(limit);
  }

}
