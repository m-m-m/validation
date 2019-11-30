/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.main.ObjectValidatorBuilder;

/**
 * The {@link ObjectValidatorBuilder builder} of {@link AbstractValidator} for {@link CharSequence} values.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderCharSequence<PARENT>
    extends CharSequenceValidatorBuilder<CharSequence, PARENT, ValidatorBuilderCharSequence<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderCharSequence(PARENT parent) {

    super(parent);
  }

  @Override
  public ValidatorBuilderCharSequence<PARENT> range(String min, String max) {

    throw new UnsupportedOperationException();
  }

}
