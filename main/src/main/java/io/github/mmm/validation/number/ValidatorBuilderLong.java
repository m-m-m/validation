/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.number;

/**
 * The {@link NumberValidatorBuilder Validator builder} for {@link Long} values.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderLong<PARENT> extends NumberValidatorBuilder<Long, PARENT, ValidatorBuilderLong<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderLong(PARENT parent) {

    super(parent);
  }

  /**
   * @see #range(io.github.mmm.base.range.Range)
   *
   * @param min the minimum allowed value.
   * @param max the maximum allowed value.
   * @return this build instance for fluent API calls.
   */
  public ValidatorBuilderLong<PARENT> range(long min, long max) {

    return range(Long.valueOf(min), Long.valueOf(max));
  }

  @Override
  protected Long parse(String string) {

    return Long.valueOf(string);
  }

}
