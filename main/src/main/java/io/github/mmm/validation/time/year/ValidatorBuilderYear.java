/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.year;

import java.time.Year;

import io.github.mmm.validation.main.ComparableValidatorBuilder;

/**
 * {@link ComparableValidatorBuilder Validator builder} for {@link Year}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderYear<PARENT>
    extends ComparableValidatorBuilder<Year, PARENT, ValidatorBuilderYear<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderYear(PARENT parent) {

    super(parent);
  }

  @Override
  protected Year parse(String value) {

    return Year.parse(value);
  }
}
