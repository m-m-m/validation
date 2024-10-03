/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.localdate;

import java.time.LocalDate;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorTemporalBefore;

/**
 * Implementation of {@link ValidatorTemporalBefore} for {@link LocalDate}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalDateBefore extends ValidatorTemporalBefore<LocalDate> {

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorLocalDateBefore(Supplier<LocalDate> valueSource) {

    super(valueSource);
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorLocalDateBefore(LocalDate value) {

    super(value);
  }

  @Override
  protected boolean isBefore(LocalDate value, LocalDate limit) {

    return value.isBefore(limit);
  }

}
