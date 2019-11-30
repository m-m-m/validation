/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.Collection;

import io.github.mmm.base.range.NumberRange;
import io.github.mmm.base.range.Range;
import io.github.mmm.validation.main.AbstractValidatorRange;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Collection) validating} that the {@link Collection#size()
 * size} of a given {@link Collection} is within a {@link #ValidatorCollectionSize(Range) predefined} {@link Range}.
 *
 * @since 1.0.0
 */
public class ValidatorCollectionSize extends AbstractValidatorRange<Collection<?>, Number> {

  /**
   * The constructor.
   *
   * @param range is the {@link Range} the value has to be {@link Range#isContained(Object) contained in}.
   */
  public ValidatorCollectionSize(Range<Number> range) {

    super(range);
  }

  /**
   *
   * The constructor.
   *
   * @param maxLength the {@link Range#getMax() maximum} {@link Collection#size() size} allowed for the
   *        {@link Collection} values.
   */
  public ValidatorCollectionSize(int maxLength) {

    this(new NumberRange(null, Integer.valueOf(maxLength)));
  }

  @Override
  protected Integer convertValue(Collection<?> value) {

    return Integer.valueOf(value.size());
  }

}
