/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.number;

/**
 * The {@link NumberValidatorBuilder Validator builder} for {@link Short} values.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderShort<PARENT>
    extends NumberValidatorBuilder<Short, PARENT, ValidatorBuilderShort<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderShort(PARENT parent) {

    super(parent);
  }

  /**
   * @see #range(io.github.mmm.base.range.Range)
   *
   * @param min the minimum allowed value.
   * @param max the maximum allowed value.
   * @return this build instance for fluent API calls.
   */
  public ValidatorBuilderShort<PARENT> range(short min, short max) {

    return range(Short.valueOf(min), Short.valueOf(max));
  }

  @Override
  protected Short parse(String string) {

    return Short.valueOf(string);
  }

}
