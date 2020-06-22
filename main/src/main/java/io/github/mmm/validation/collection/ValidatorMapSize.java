/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.Map;

import io.github.mmm.base.range.NumberRangeType;
import io.github.mmm.base.range.Range;
import io.github.mmm.validation.main.AbstractValidatorRange;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Map) validating} that the {@link Map#size() size} of a
 * given {@link Map} is within a predefined {@link Range}.
 *
 * @since 1.0.0
 */
public class ValidatorMapSize extends AbstractValidatorRange<Map<?, ?>, Number> {

  /**
   * The constructor.
   *
   * @param range is the {@link Range} the value has to be {@link Range#contains(Object) contained in}.
   */
  public ValidatorMapSize(Range<Number> range) {

    super(range);
  }

  /**
   *
   * The constructor.
   *
   * @param maxLength the {@link Range#getMax() maximum} {@link Map#size() size} allowed for the {@link Map} values.
   */
  public ValidatorMapSize(int maxLength) {

    this(new NumberRangeType(null, Integer.valueOf(maxLength)));
  }

  @Override
  protected Integer convertValue(Map<?, ?> value) {

    return Integer.valueOf(value.size());
  }

  @Override
  protected boolean isLength() {

    return true;
  }

}
