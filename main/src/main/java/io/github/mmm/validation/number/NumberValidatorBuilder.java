/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.number;

import io.github.mmm.base.range.NumberRange;
import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.main.ComparableValidatorBuilder;
import io.github.mmm.validation.main.ValidatorRange;

/**
 * {@link ComparableValidatorBuilder Validator builder} for {@link Number} values.
 *
 * @param <V> the generic type of the value to {@link AbstractValidator#validate(Object) validate}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 * @param <SELF> the generic type of this builder itself (this).
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class NumberValidatorBuilder<V extends Number & Comparable, PARENT, SELF extends ComparableValidatorBuilder<V, PARENT, SELF>>
    extends ComparableValidatorBuilder<V, PARENT, SELF> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public NumberValidatorBuilder(PARENT parent) {

    super(parent);
  }

  @Override
  public SELF range(String min, String max) {

    Double dMin = null;
    if (min != null) {
      dMin = Double.valueOf(min);
    }
    Double dMax = null;
    if (max != null) {
      dMax = Double.valueOf(max);
    }
    add(new ValidatorRange<>(new NumberRange(dMin, dMax)));
    return self();
  }

}
