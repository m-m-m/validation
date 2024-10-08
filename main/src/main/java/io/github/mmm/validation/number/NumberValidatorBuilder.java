/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.number;

import io.github.mmm.base.range.NumberRangeType;
import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.main.ComparableValidatorBuilder;

/**
 * {@link ComparableValidatorBuilder Validator builder} for {@link Number} values.
 *
 * @param <V> the generic type of the value to {@link AbstractValidator#validate(Object) validate}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 * @param <SELF> the generic type of this builder itself (this).
 *
 * @since 1.0.0
 */
public abstract class NumberValidatorBuilder<V extends Number & Comparable<?>, PARENT, SELF extends ComparableValidatorBuilder<V, PARENT, SELF>>
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

    V vMin = null;
    if (min != null) {
      vMin = parse(min);
    }
    V vMax = null;
    if (max != null) {
      vMax = parse(max);
    }
    range(vMin, vMax);
    return self();
  }

  @Override
  public SELF range(V min, V max) {

    if ((min != null) || (max != null)) {
      return range(new NumberRangeType<>(min, max));
    }
    return self();
  }

}
