/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.zoneddatetime;

import java.time.ZonedDateTime;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorBuilderTemporal;

/**
 * {@link ValidatorBuilderTemporal Validator builder} for {@link ZonedDateTime}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderZonedDateTime<PARENT>
    extends ValidatorBuilderTemporal<ZonedDateTime, PARENT, ValidatorBuilderZonedDateTime<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderZonedDateTime(PARENT parent) {

    super(parent);
  }

  @Override
  protected ZonedDateTime parse(String value) {

    return ZonedDateTime.parse(value);
  }

  @Override
  public ValidatorBuilderZonedDateTime<PARENT> past() {

    return add(new ValidatorZonedDateTimePast());
  }

  @Override
  public ValidatorBuilderZonedDateTime<PARENT> future() {

    return add(new ValidatorZonedDateTimeFuture());
  }

  @Override
  public ValidatorBuilderZonedDateTime<PARENT> after(ZonedDateTime value) {

    return add(new ValidatorZonedDateTimeAfter(value));
  }

  @Override
  public ValidatorBuilderZonedDateTime<PARENT> after(Supplier<ZonedDateTime> valueSource) {

    return add(new ValidatorZonedDateTimeAfter(valueSource));
  }

  @Override
  public ValidatorBuilderZonedDateTime<PARENT> before(ZonedDateTime value) {

    return add(new ValidatorZonedDateTimeBefore(value));
  }

  @Override
  public ValidatorBuilderZonedDateTime<PARENT> before(Supplier<ZonedDateTime> valueSource) {

    return add(new ValidatorZonedDateTimeBefore(valueSource));
  }

}
