/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.number;

import java.math.BigInteger;

/**
 * The {@link NumberValidatorBuilder Validator builder} for {@link BigInteger} values.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderBigInteger<PARENT>
    extends NumberValidatorBuilder<BigInteger, PARENT, ValidatorBuilderBigInteger<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderBigInteger(PARENT parent) {

    super(parent);
  }

  @Override
  protected BigInteger parse(String string) {

    return new BigInteger(string);
  }

}
