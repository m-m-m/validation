/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.dayofweek;

import java.time.DayOfWeek;
import java.util.Locale;

import io.github.mmm.validation.main.ComparableValidatorBuilder;

/**
 * {@link ComparableValidatorBuilder Validator builder} for {@link DayOfWeek}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderDayOfWeek<PARENT>
    extends ComparableValidatorBuilder<DayOfWeek, PARENT, ValidatorBuilderDayOfWeek<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderDayOfWeek(PARENT parent) {

    super(parent);
  }

  @Override
  protected DayOfWeek parse(String value) {

    if (value == null) {
      return null;
    }
    if (value.length() <= 1) {
      int m = Integer.parseInt(value);
      return DayOfWeek.of(m);
    } else {
      return DayOfWeek.valueOf(value.toUpperCase(Locale.ROOT));
    }
  }
}
