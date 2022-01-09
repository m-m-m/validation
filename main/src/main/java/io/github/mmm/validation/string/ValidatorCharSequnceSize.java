/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import java.util.Collection;

import io.github.mmm.base.range.NumberRangeType;
import io.github.mmm.base.range.Range;
import io.github.mmm.validation.main.AbstractValidatorRange;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence) validating} that the
 * {@link CharSequence#length() length} of a given {@link CharSequence} is within a
 * {@link #ValidatorCharSequnceSize(Range) predefined} {@link Range}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class ValidatorCharSequnceSize extends AbstractValidatorRange<CharSequence, Integer> {

  /**
   * The constructor.
   *
   * @param range is the {@link Range} the value has to be {@link Range#contains(Comparable) contained in}.
   */
  public ValidatorCharSequnceSize(Range<Integer> range) {

    super(range);
  }

  /**
   *
   * The constructor.
   *
   * @param maxLength the {@link Range#getMax() maximum} {@link Collection#size() size} allowed for the
   *        {@link Collection} values.
   */
  public ValidatorCharSequnceSize(int maxLength) {

    this(new NumberRangeType<>(null, Integer.valueOf(maxLength)));
  }

  @Override
  protected Integer convertValue(CharSequence value) {

    return Integer.valueOf(value.length());
  }

  @Override
  protected boolean isLength() {

    return true;
  }

}
