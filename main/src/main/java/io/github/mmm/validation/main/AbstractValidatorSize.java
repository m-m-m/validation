/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import io.github.mmm.base.range.Range;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Object, Object) validating} that a given value has a size
 * or length that {@link Range#contains(Comparable) is contained} in a given {@link Range}.
 *
 * @param <V> the generic type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public abstract class AbstractValidatorSize<V> extends AbstractValidatorRange<V, Integer> {

  /**
   * The constructor.
   *
   * @param range is the {@link Range} the value has to be {@link Range#contains(Comparable) contained in}.
   */
  public AbstractValidatorSize(Range<Integer> range) {

    super(range);
    if (isLess(range.getMin(), 0) || isLess(range.getMax(), 1)) {
      throw new IllegalArgumentException(range.toString());
    }
  }

  private boolean isLess(Integer bound, int min) {

    if (bound != null) {
      if (bound.intValue() < min) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected final boolean isLength() {

    return true;
  }

}
