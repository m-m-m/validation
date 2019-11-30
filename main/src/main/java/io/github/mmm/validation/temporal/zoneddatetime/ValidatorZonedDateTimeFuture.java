/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.zoneddatetime;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import io.github.mmm.validation.temporal.ValidatorTemporalFuture;

/**
 * Implementation of {@link ValidatorTemporalFuture} for {@link OffsetDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorZonedDateTimeFuture extends ValidatorTemporalFuture<ZonedDateTime> {

  @Override
  protected boolean isFuture(ZonedDateTime value) {

    return value.isAfter(ZonedDateTime.now());
  }

}
