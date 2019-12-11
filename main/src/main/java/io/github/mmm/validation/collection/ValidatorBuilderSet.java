/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.Set;

import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.main.ObjectValidatorBuilder;

/**
 * The {@link ObjectValidatorBuilder builder} of {@link AbstractValidator} for a {@link Set} of {@link Object}s.
 *
 * @param <E> the generic type of the {@link Set#contains(Object) elements contained} in the {@link Set}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderSet<E, PARENT>
    extends AbstractCollectionValidatorBuilder<E, Set<E>, PARENT, ValidatorBuilderSet<E, PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderSet(PARENT parent) {

    super(parent);
  }

}
