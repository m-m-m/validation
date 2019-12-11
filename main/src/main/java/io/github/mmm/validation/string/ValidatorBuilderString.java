/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

/**
 * {@link CharSequenceValidatorBuilder Validator builder} for {@link String}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderString<PARENT>
    extends CharSequenceValidatorBuilder<String, PARENT, ValidatorBuilderString<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderString(PARENT parent) {

    super(parent);
  }

}
