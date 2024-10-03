/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.localdatetime;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorBuilderTemporal;

/**
 * {@link ValidatorBuilderTemporal Validator builder} for {@link LocalDateTime}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderLocalDateTime<PARENT>
    extends ValidatorBuilderTemporal<LocalDateTime, PARENT, ValidatorBuilderLocalDateTime<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderLocalDateTime(PARENT parent) {

    super(parent);
  }

  @Override
  protected LocalDateTime parse(String value) {

    return LocalDateTime.parse(value);
  }

  @Override
  public ValidatorBuilderLocalDateTime<PARENT> past() {

    return add(new ValidatorLocalDateTimePast());
  }

  @Override
  public ValidatorBuilderLocalDateTime<PARENT> future() {

    return add(new ValidatorLocalDateTimeFuture());
  }

  @Override
  public ValidatorBuilderLocalDateTime<PARENT> after(LocalDateTime value) {

    return add(new ValidatorLocalDateTimeAfter(value));
  }

  @Override
  public ValidatorBuilderLocalDateTime<PARENT> after(Supplier<LocalDateTime> valueSource) {

    return add(new ValidatorLocalDateTimeAfter(valueSource));
  }

  @Override
  public ValidatorBuilderLocalDateTime<PARENT> before(LocalDateTime value) {

    return add(new ValidatorLocalDateTimeBefore(value));
  }

  @Override
  public ValidatorBuilderLocalDateTime<PARENT> before(Supplier<LocalDateTime> valueSource) {

    return add(new ValidatorLocalDateTimeBefore(valueSource));
  }

}
