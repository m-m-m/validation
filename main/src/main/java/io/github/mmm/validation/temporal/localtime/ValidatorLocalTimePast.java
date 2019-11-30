/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.localtime;

import java.time.LocalTime;

import io.github.mmm.validation.temporal.ValidatorTemporalPast;

/**
 * Implementation of {@link ValidatorTemporalPast} for {@link LocalTime}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalTimePast extends ValidatorTemporalPast<LocalTime> {

  @Override
  protected boolean isPast(LocalTime value) {

    return value.isBefore(LocalTime.now());
  }

}
