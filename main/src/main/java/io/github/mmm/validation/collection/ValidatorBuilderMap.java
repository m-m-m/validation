/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.Map;

import io.github.mmm.base.range.GenericRange;
import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.main.ObjectValidatorBuilder;

/**
 * The {@link ObjectValidatorBuilder builder} of {@link AbstractValidator} for a {@link Map}.
 *
 * @param <K> the generic type of the {@link java.util.Map.Entry#getKey() keys}.
 * @param <V> the generic type of the {@link java.util.Map.Entry#getValue() values}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderMap<K, V, PARENT>
    extends AbstractMapValidatorBuilder<K, V, Map<K, V>, PARENT, ValidatorBuilderMap<K, V, PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderMap(PARENT parent) {

    super(parent);
  }

  @Override
  public ValidatorBuilderMap<K, V, PARENT> range(String min, String max) {

    if ((min != null) || (max != null)) {
      Integer iMin = null;
      if (min != null) {
        iMin = Integer.valueOf(min);
      }
      Integer iMax = null;
      if (max != null) {
        iMax = Integer.valueOf(max);
      }
      add(new ValidatorMapSize(new GenericRange<>(iMin, iMax)));
    }
    return self();
  }

}
