/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.List;

import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.main.ObjectValidatorBuilder;

/**
 * The {@link ObjectValidatorBuilder builder} of {@link AbstractValidator} for a {@link List} of {@link Object}s.
 *
 * @param <E> the generic type of the {@link List#contains(Object) elements contained} in the {@link List}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderList<E, PARENT>
    extends AbstractCollectionValidatorBuilder<E, List<E>, PARENT, ValidatorBuilderList<E, PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderList(PARENT parent) {

    super(parent);
  }

}
