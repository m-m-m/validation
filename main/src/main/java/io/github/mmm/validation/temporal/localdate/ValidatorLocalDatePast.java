/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.localdate;

import java.time.LocalDate;

import io.github.mmm.validation.temporal.ValidatorTemporalPast;

/**
 * Implementation of {@link ValidatorTemporalPast} for {@link LocalDate}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalDatePast extends ValidatorTemporalPast<LocalDate> {

  @Override
  protected boolean isPast(LocalDate value) {

    return value.isBefore(LocalDate.now());
  }

}
