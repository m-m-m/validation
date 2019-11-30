/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

/**
 * {@link ObjectValidatorBuilder Validator builder} for {@link Boolean}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderBoolean<PARENT>
    extends ObjectValidatorBuilder<Boolean, PARENT, ValidatorBuilderBoolean<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderBoolean(PARENT parent) {

    super(parent);
  }

  @Override
  public ValidatorBuilderBoolean<PARENT> range(String min, String max) {

    throw new UnsupportedOperationException("Min/max constraints not avilable for Boolean");
  }

}
