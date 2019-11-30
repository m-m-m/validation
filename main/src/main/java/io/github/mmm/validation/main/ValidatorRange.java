/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import io.github.mmm.base.range.GenericRange;
import io.github.mmm.base.range.Range;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Object, Object) validating} that a given value is within
 * a specific {@link Range}.
 *
 * @param <V> is the generic type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public class ValidatorRange<V> extends AbstractValidatorRange<V, V> {

  /**
   * The constructor.
   *
   * @param range is the {@link Range} the value has to be within.
   */
  public ValidatorRange(Range<V> range) {

    super(range);
  }

  /**
   * The constructor.
   *
   * @param min is the {@link Range#getMin() minimum} value allowed.
   * @param max is the {@link Range#getMax() maximum} value allowed.
   */
  public ValidatorRange(V min, V max) {

    this(new GenericRange<>(min, max));
  }

}
