/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import io.github.mmm.base.range.GenericRange;
import io.github.mmm.base.range.Range;
import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.collection.ValidatorCollectionSize;

/**
 * The {@link ObjectValidatorBuilder builder} of {@link AbstractValidator} for {@link Comparable} values.
 *
 * @param <V> the generic type of the value to {@link AbstractValidator#validate(Object) validate}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 * @param <SELF> the generic type of this builder itself (this).
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public abstract class ComparableValidatorBuilder<V extends Comparable, PARENT, SELF extends ComparableValidatorBuilder<V, PARENT, SELF>>
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

    return range(new GenericRange<>(min, max));
  }

}
