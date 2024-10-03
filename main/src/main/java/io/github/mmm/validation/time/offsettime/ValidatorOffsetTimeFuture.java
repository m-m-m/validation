/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.offsettime;

import java.time.OffsetTime;

import io.github.mmm.validation.time.ValidatorTemporalFuture;

/**
 * Implementation of {@link ValidatorTemporalFuture} for {@link OffsetTime}.
 *
 * @since 1.0.0
 */
public class ValidatorOffsetTimeFuture extends ValidatorTemporalFuture<OffsetTime> {

  @Override
  protected boolean isFuture(OffsetTime value) {

    return value.isAfter(OffsetTime.now());
  }

}
