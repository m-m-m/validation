/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.localtime;

import java.time.LocalTime;

import io.github.mmm.validation.temporal.ValidatorTemporalFuture;

/**
 * Implementation of {@link ValidatorTemporalFuture} for {@link LocalTime}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalTimeFuture extends ValidatorTemporalFuture<LocalTime> {

  @Override
  protected boolean isFuture(LocalTime value) {

    return value.isAfter(LocalTime.now());
  }

}
