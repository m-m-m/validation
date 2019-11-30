/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.Collection;

import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.main.ObjectValidatorBuilder;
import io.github.mmm.validation.string.ValidatorBuilderString;

/**
 * The {@link ObjectValidatorBuilder builder} of {@link AbstractValidator} for a {@link Collection} of {@link String}s.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderCollectionOfString<PARENT> extends ValidatorBuilderCollection<String, PARENT> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderCollectionOfString(PARENT parent) {

    super(parent);
  }

  /**
   * @return the {@link #with(java.util.function.BiFunction) sub-validator builder} for the {@link Collection} elements.
   */
  public ValidatorBuilderString<ValidatorBuilderCollection<String, PARENT>> with() {

    return with((f, v) -> f.create(v));
  }

}
