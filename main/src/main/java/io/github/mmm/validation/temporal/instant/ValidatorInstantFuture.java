/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.instant;

import java.time.Instant;

import io.github.mmm.validation.temporal.ValidatorTemporalFuture;

/**
 * Implementation of {@link ValidatorTemporalFuture} for {@link Instant}.
 *
 * @since 1.0.0
 */
public class ValidatorInstantFuture extends ValidatorTemporalFuture<Instant> {

  @Override
  protected boolean isFuture(Instant value) {

    return value.isAfter(Instant.now());
  }

}
