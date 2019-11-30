/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.number;

/**
 * {@link NumberValidatorBuilder Validator builder} for {@link Byte} values.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 7.6.0
 */
public class ValidatorBuilderByte<PARENT> extends NumberValidatorBuilder<Byte, PARENT, ValidatorBuilderByte<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderByte(PARENT parent) {

    super(parent);
  }

  /**
   * @see #range(io.github.mmm.base.range.Range)
   *
   * @param min the minimum allowed value.
   * @param max the maximum allowed value.
   * @return this build instance for fluent API calls.
   */
  public ValidatorBuilderByte<PARENT> range(byte min, byte max) {

    return range(Byte.valueOf(min), Byte.valueOf(max));
  }

}
