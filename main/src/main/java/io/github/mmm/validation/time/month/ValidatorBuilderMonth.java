/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.month;

import java.time.Month;
import java.util.Locale;

import io.github.mmm.validation.main.ComparableValidatorBuilder;

/**
 * {@link ComparableValidatorBuilder Validator builder} for {@link Month}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderMonth<PARENT>
    extends ComparableValidatorBuilder<Month, PARENT, ValidatorBuilderMonth<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderMonth(PARENT parent) {

    super(parent);
  }

  @Override
  protected Month parse(String value) {

    if (value == null) {
      return null;
    }
    if (value.length() <= 2) {
      int m = Integer.parseInt(value);
      return Month.of(m);
    } else {
      return Month.valueOf(value.toUpperCase(Locale.ROOT));
    }
  }
}
