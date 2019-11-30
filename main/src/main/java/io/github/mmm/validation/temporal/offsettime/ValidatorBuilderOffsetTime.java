/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.offsettime;

import java.time.OffsetTime;
import java.util.function.Supplier;

import io.github.mmm.validation.temporal.ValidatorBuilderTemporal;

/**
 * {@link ValidatorBuilderTemporal Validator builder} for {@link OffsetTime}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderOffsetTime<PARENT>
    extends ValidatorBuilderTemporal<OffsetTime, PARENT, ValidatorBuilderOffsetTime<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderOffsetTime(PARENT parent) {

    super(parent);
  }

  @Override
  protected OffsetTime parse(String value) {

    return OffsetTime.parse(value);
  }

  @Override
  public ValidatorBuilderOffsetTime<PARENT> past() {

    return add(new ValidatorOffsetTimePast());
  }

  @Override
  public ValidatorBuilderOffsetTime<PARENT> future() {

    return add(new ValidatorOffsetTimeFuture());
  }

  @Override
  public ValidatorBuilderOffsetTime<PARENT> after(OffsetTime value) {

    return add(new ValidatorOffsetTimeAfter(value));
  }

  @Override
  public ValidatorBuilderOffsetTime<PARENT> after(Supplier<OffsetTime> valueSource) {

    return add(new ValidatorOffsetTimeAfter(valueSource));
  }

  @Override
  public ValidatorBuilderOffsetTime<PARENT> before(OffsetTime value) {

    return add(new ValidatorOffsetTimeBefore(value));
  }

  @Override
  public ValidatorBuilderOffsetTime<PARENT> before(Supplier<OffsetTime> valueSource) {

    return add(new ValidatorOffsetTimeBefore(valueSource));
  }

}
