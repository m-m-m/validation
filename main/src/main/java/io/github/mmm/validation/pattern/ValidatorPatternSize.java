/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.pattern;

import java.util.Collection;
import java.util.regex.Pattern;

import io.github.mmm.base.range.NumberRangeType;
import io.github.mmm.base.range.Range;
import io.github.mmm.validation.main.AbstractValidatorSize;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Pattern) validating} that the {@link String#length()
 * length} of a given {@link Pattern#pattern() pattern} is within a {@link #ValidatorPatternSize(Range) predefined}
 * {@link Range}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class ValidatorPatternSize extends AbstractValidatorSize<Pattern> {

  /**
   * The constructor.
   *
   * @param range is the {@link Range} the value has to be {@link Range#contains(Comparable) contained in}.
   */
  public ValidatorPatternSize(Range<Integer> range) {

    super(range);
  }

  /**
   *
   * The constructor.
   *
   * @param maxLength the {@link Range#getMax() maximum} {@link Collection#size() size} allowed for the
   *        {@link Collection} values.
   */
  public ValidatorPatternSize(int maxLength) {

    this(new NumberRangeType<>(null, Integer.valueOf(maxLength)));
  }

  @Override
  protected Integer convertValue(Pattern value) {

    if (value == null) {
      return Integer.valueOf(0);
    }
    return Integer.valueOf(value.pattern().length());
  }

}
