/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import java.util.regex.Pattern;

import io.github.mmm.base.range.NumberRangeType;
import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.main.ObjectValidatorBuilder;

/**
 * {@link ObjectValidatorBuilder Validator builder} for {@link CharSequence}.
 *
 * @param <V> the generic type of the value to {@link AbstractValidator#validate(Object) validate}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 * @param <SELF> the generic type of this builder itself (this).
 *
 * @since 1.0.0
 */
public abstract class CharSequenceValidatorBuilder<V extends CharSequence, PARENT, SELF extends CharSequenceValidatorBuilder<V, PARENT, SELF>>
    extends ObjectValidatorBuilder<V, PARENT, SELF> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public CharSequenceValidatorBuilder(PARENT parent) {

    super(parent);
  }

  /**
   * @param pattern the regular expression {@link Pattern} to match.
   * @return this build instance for fluent API calls.
   */
  public SELF pattern(String pattern) {

    return pattern(Pattern.compile(pattern));
  }

  /**
   * @param pattern the regular expression {@link Pattern} to match.
   * @return this build instance for fluent API calls.
   */
  public SELF pattern(Pattern pattern) {

    return add(new ValidatorPattern(pattern));
  }

  /**
   * @param min the minimum length allowed.
   * @return this build instance for fluent API calls.
   */
  public SELF min(int min) {

    return range(Integer.valueOf(min), null);
  }

  /**
   * @param max the maximum length allowed.
   * @return this build instance for fluent API calls.
   */
  public SELF max(int max) {

    return range(null, Integer.valueOf(max));
  }

  /**
   * @param min the minimum length allowed or {@code null} for no lower bound.
   * @param max the maximum length allowed or {@code null} for no upper bound.
   * @return this build instance for fluent API calls.
   */
  public SELF range(Integer min, Integer max) {

    if ((min != null) || (max != null)) {
      add(new ValidatorCharSequnceSize(new NumberRangeType(min, max)));
    }
    return self();
  }

  @Override
  public SELF range(String min, String max) {

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
