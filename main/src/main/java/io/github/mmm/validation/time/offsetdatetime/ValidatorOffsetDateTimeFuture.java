/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.offsetdatetime;

import java.time.OffsetDateTime;

import io.github.mmm.validation.time.ValidatorTemporalFuture;

/**
 * Implementation of {@link ValidatorTemporalFuture} for {@link OffsetDateTime}.
 *
 * @since 1.0.0
 */
public class ValidatorOffsetDateTimeFuture extends ValidatorTemporalFuture<OffsetDateTime> {

  @Override
  protected boolean isFuture(OffsetDateTime value) {

    return value.isAfter(OffsetDateTime.now());
  }

}
