/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import io.github.mmm.base.range.Range;
import io.github.mmm.base.range.RangeType;
import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.collection.ValidatorCollectionSize;
import io.github.mmm.validation.range.ValidatorRange;

/**
 * The {@link ObjectValidatorBuilder builder} of {@link AbstractValidator} for {@link Comparable} values.
 *
 * @param <V> the generic type of the value to {@link AbstractValidator#validate(Object) validate}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 * @param <SELF> the generic type of this builder itself (this).
 *
 * @since 1.0.0
 */
public abstract class ComparableValidatorBuilder<V extends Comparable<?>, PARENT, SELF extends ComparableValidatorBuilder<V, PARENT, SELF>>
    extends ObjectValidatorBuilder<V, PARENT, SELF> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ComparableValidatorBuilder(PARENT parent) {

    super(parent);
  }

  /**
   * @param value the value as {@link String}.
   * @return the parsed value.
   */
  protected abstract V parse(String value);

  /**
   * @see ValidatorCollectionSize
   *
   * @param range the {@link Range} to limit the value (or its size).
   * @return this build instance for fluent API calls.
   */
  public SELF range(Range<V> range) {

    return add(new ValidatorRange<>(range));
  }

  /**
   * @see #range(Range)
   *
   * @param min the minimum value.
   * @param max the maximum value.
   * @return this build instance for fluent API calls.
   */
  public SELF range(V min, V max) {

    if ((min != null) || (max != null)) {
      range(RangeType.of(min, max));
    }
    return self();
  }

  @Override
  public SELF range(String min, String max) {

    V minimum = null;
    if (min != null) {
      minimum = parse(min);
    }
    V maximum = null;
    if (max != null) {
      maximum = parse(max);
    }
    return range(minimum, maximum);
  }

}
