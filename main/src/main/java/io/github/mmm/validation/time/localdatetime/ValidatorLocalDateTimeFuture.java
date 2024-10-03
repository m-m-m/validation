/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.localdatetime;

import java.time.LocalDateTime;

import io.github.mmm.validation.time.ValidatorTemporalFuture;

/**
 * Implementation of {@link ValidatorTemporalFuture} for {@link LocalDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalDateTimeFuture extends ValidatorTemporalFuture<LocalDateTime> {

  @Override
  protected boolean isFuture(LocalDateTime value) {

    return value.isAfter(LocalDateTime.now());
  }

}
