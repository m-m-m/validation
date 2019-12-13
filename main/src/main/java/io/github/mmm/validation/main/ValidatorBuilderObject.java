/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

/**
 * {@link ObjectValidatorBuilder Validator builder} for {@link Object}.
 *
 * @param <V> type of the object to validate.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderObject<V, PARENT>
    extends ObjectValidatorBuilder<V, PARENT, ValidatorBuilderObject<V, PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderObject(PARENT parent) {

    super(parent);
  }

  @Override
  public ValidatorBuilderObject<V, PARENT> range(String min, String max) {

    throw new UnsupportedOperationException("Min/max constraints not avilable for Object");
  }

}
