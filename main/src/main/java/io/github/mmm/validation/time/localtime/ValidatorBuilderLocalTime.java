/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.localtime;

import java.time.LocalTime;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorBuilderTemporal;

/**
 * {@link ValidatorBuilderTemporal Validator builder} for {@link LocalTime}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderLocalTime<PARENT>
    extends ValidatorBuilderTemporal<LocalTime, PARENT, ValidatorBuilderLocalTime<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderLocalTime(PARENT parent) {

    super(parent);
  }

  @Override
  protected LocalTime parse(String value) {

    return LocalTime.parse(value);
  }

  @Override
  public ValidatorBuilderLocalTime<PARENT> past() {

    return add(new ValidatorLocalTimePast());
  }

  @Override
  public ValidatorBuilderLocalTime<PARENT> future() {

    return add(new ValidatorLocalTimeFuture());
  }

  @Override
  public ValidatorBuilderLocalTime<PARENT> after(LocalTime value) {

    return add(new ValidatorLocalTimeAfter(value));
  }

  @Override
  public ValidatorBuilderLocalTime<PARENT> after(Supplier<LocalTime> valueSource) {

    return add(new ValidatorLocalTimeAfter(valueSource));
  }

  @Override
  public ValidatorBuilderLocalTime<PARENT> before(LocalTime value) {

    return add(new ValidatorLocalTimeBefore(value));
  }

  @Override
  public ValidatorBuilderLocalTime<PARENT> before(Supplier<LocalTime> valueSource) {

    return add(new ValidatorLocalTimeBefore(valueSource));
  }

}
