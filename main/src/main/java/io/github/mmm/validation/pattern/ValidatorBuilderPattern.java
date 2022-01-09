/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.pattern;

import java.util.regex.Pattern;

import io.github.mmm.base.range.NumberRangeType;
import io.github.mmm.validation.main.ObjectValidatorBuilder;
import io.github.mmm.validation.string.CharSequenceValidatorBuilder;

/**
 * {@link CharSequenceValidatorBuilder Validator builder} for {@link String}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderPattern<PARENT>
    extends ObjectValidatorBuilder<Pattern, PARENT, ValidatorBuilderPattern<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderPattern(PARENT parent) {

    super(parent);
  }

  /**
   * @param min the minimum length allowed or {@code null} for no lower bound.
   * @param max the maximum length allowed or {@code null} for no upper bound.
   * @return this build instance for fluent API calls.
   */
  public ValidatorBuilderPattern<PARENT> range(Integer min, Integer max) {

    if ((min != null) || (max != null)) {
      add(new ValidatorPatternSize(new NumberRangeType<>(min, max)));
    }
    return self();
  }

  @Override
  public ValidatorBuilderPattern<PARENT> range(String min, String max) {

    Integer iMin = null;
    if (min != null) {
      iMin = Integer.valueOf(min);
    }
    Integer iMax = null;
    if (max != null) {
      iMax = Integer.valueOf(max);
    }
    return range(iMin, iMax);
  }

}
