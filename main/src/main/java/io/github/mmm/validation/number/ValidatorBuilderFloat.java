/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.number;

/**
 * The {@link NumberValidatorBuilder Validator builder} for {@link Float} values.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderFloat<PARENT>
    extends NumberValidatorBuilder<Float, PARENT, ValidatorBuilderFloat<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderFloat(PARENT parent) {

    super(parent);
  }

  /**
   * @see #range(io.github.mmm.base.range.Range)
   *
   * @param min the minimum allowed value.
   * @param max the maximum allowed value.
   * @return this build instance for fluent API calls.
   */
  public ValidatorBuilderFloat<PARENT> range(float min, float max) {

    return range(Float.valueOf(min), Float.valueOf(max));
  }

  @Override
  protected Float parse(String string) {

    return Float.valueOf(string);
  }

}
