/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.instant;

import java.time.Instant;

import io.github.mmm.validation.time.ValidatorTemporalPast;

/**
 * Implementation of {@link ValidatorTemporalPast} for {@link Instant}.
 *
 * @since 1.0.0
 */
public class ValidatorInstantPast extends ValidatorTemporalPast<Instant> {

  @Override
  protected boolean isPast(Instant value) {

    return value.isBefore(Instant.now());
  }

}
