/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.localdatetime;

import java.time.LocalDateTime;

import io.github.mmm.validation.time.ValidatorTemporalPast;

/**
 * Implementation of {@link ValidatorTemporalPast} for {@link LocalDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorLocalDateTimePast extends ValidatorTemporalPast<LocalDateTime> {

  @Override
  protected boolean isPast(LocalDateTime value) {

    return value.isBefore(LocalDateTime.now());
  }

}
