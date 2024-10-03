/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.localdate;

import java.time.LocalDate;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorBuilderTemporal;

/**
 * {@link ValidatorBuilderTemporal Validator builder} for {@link LocalDate}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderLocalDate<PARENT>
    extends ValidatorBuilderTemporal<LocalDate, PARENT, ValidatorBuilderLocalDate<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderLocalDate(PARENT parent) {

    super(parent);
  }

  @Override
  protected LocalDate parse(String value) {

    return LocalDate.parse(value);
  }

  @Override
  public ValidatorBuilderLocalDate<PARENT> past() {

    return add(new ValidatorLocalDatePast());
  }

  @Override
  public ValidatorBuilderLocalDate<PARENT> future() {

    return add(new ValidatorLocalDateFuture());
  }

  @Override
  public ValidatorBuilderLocalDate<PARENT> after(LocalDate value) {

    return add(new ValidatorLocalDateAfter(value));
  }

  @Override
  public ValidatorBuilderLocalDate<PARENT> after(Supplier<LocalDate> valueSource) {

    return add(new ValidatorLocalDateAfter(valueSource));
  }

  @Override
  public ValidatorBuilderLocalDate<PARENT> before(LocalDate value) {

    return add(new ValidatorLocalDateBefore(value));
  }

  @Override
  public ValidatorBuilderLocalDate<PARENT> before(Supplier<LocalDate> valueSource) {

    return add(new ValidatorLocalDateBefore(valueSource));
  }

}
