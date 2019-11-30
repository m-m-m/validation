/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.offsetdatetime;

import java.time.OffsetDateTime;

import io.github.mmm.validation.temporal.ValidatorTemporalPast;

/**
 * Implementation of {@link ValidatorTemporalPast} for {@link OffsetDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorOffsetDateTimePast extends ValidatorTemporalPast<OffsetDateTime> {

  @Override
  protected boolean isPast(OffsetDateTime value) {

    return value.isBefore(OffsetDateTime.now());
  }

}
