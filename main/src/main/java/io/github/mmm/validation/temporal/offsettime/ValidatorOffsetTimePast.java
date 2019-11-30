/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.offsettime;

import java.time.OffsetTime;

import io.github.mmm.validation.temporal.ValidatorTemporalPast;

/**
 * Implementation of {@link ValidatorTemporalPast} for {@link OffsetTime}.
 *
 * @since 1.0.0
 */
public class ValidatorOffsetTimePast extends ValidatorTemporalPast<OffsetTime> {

  @Override
  protected boolean isPast(OffsetTime value) {

    return value.isBefore(OffsetTime.now());
  }

}
