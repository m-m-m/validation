/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.zoneddatetime;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import io.github.mmm.validation.temporal.ValidatorTemporalPast;

/**
 * Implementation of {@link ValidatorTemporalPast} for {@link OffsetDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorZonedDateTimePast extends ValidatorTemporalPast<ZonedDateTime> {

  @Override
  protected boolean isPast(ZonedDateTime value) {

    return value.isBefore(ZonedDateTime.now());
  }

}
