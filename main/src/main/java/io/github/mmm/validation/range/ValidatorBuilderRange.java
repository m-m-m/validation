/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.range;

import io.github.mmm.base.range.Range;
import io.github.mmm.validation.main.ObjectValidatorBuilder;
import io.github.mmm.validation.string.CharSequenceValidatorBuilder;

/**
 * {@link CharSequenceValidatorBuilder Validator builder} for {@link String}.
 *
 * @param <V> type of the {@link Range} bounds.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class ValidatorBuilderRange<V extends Comparable, PARENT>
    extends ObjectValidatorBuilder<Range<V>, PARENT, ValidatorBuilderRange<V, PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderRange(PARENT parent) {

    super(parent);
  }

  @Override
  public ValidatorBuilderRange<V, PARENT> range(String min, String max) {

    throw new UnsupportedOperationException();
  }

}
