/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal.offsetdatetime;

import java.time.OffsetDateTime;
import java.util.function.Supplier;

import io.github.mmm.validation.temporal.ValidatorBuilderTemporal;

/**
 * {@link ValidatorBuilderTemporal Validator builder} for {@link OffsetDateTime}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderOffsetDateTime<PARENT>
    extends ValidatorBuilderTemporal<OffsetDateTime, PARENT, ValidatorBuilderOffsetDateTime<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderOffsetDateTime(PARENT parent) {

    super(parent);
  }

  @Override
  protected OffsetDateTime parse(String value) {

    return OffsetDateTime.parse(value);
  }

  @Override
  public ValidatorBuilderOffsetDateTime<PARENT> past() {

    return add(new ValidatorOffsetDateTimePast());
  }

  @Override
  public ValidatorBuilderOffsetDateTime<PARENT> future() {

    return add(new ValidatorOffsetDateTimeFuture());
  }

  @Override
  public ValidatorBuilderOffsetDateTime<PARENT> after(OffsetDateTime value) {

    return add(new ValidatorOffsetDateTimeAfter(value));
  }

  @Override
  public ValidatorBuilderOffsetDateTime<PARENT> after(Supplier<OffsetDateTime> valueSource) {

    return add(new ValidatorOffsetDateTimeAfter(valueSource));
  }

  @Override
  public ValidatorBuilderOffsetDateTime<PARENT> before(OffsetDateTime value) {

    return add(new ValidatorOffsetDateTimeBefore(value));
  }

  @Override
  public ValidatorBuilderOffsetDateTime<PARENT> before(Supplier<OffsetDateTime> valueSource) {

    return add(new ValidatorOffsetDateTimeBefore(valueSource));
  }

}
