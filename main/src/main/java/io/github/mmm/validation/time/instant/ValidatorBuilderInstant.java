/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time.instant;

import java.time.Instant;
import java.util.function.Supplier;

import io.github.mmm.validation.time.ValidatorBuilderTemporal;

/**
 * {@link ValidatorBuilderTemporal Validator builder} for {@link Instant}.
 *
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 *
 * @since 1.0.0
 */
public class ValidatorBuilderInstant<PARENT>
    extends ValidatorBuilderTemporal<Instant, PARENT, ValidatorBuilderInstant<PARENT>> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderInstant(PARENT parent) {

    super(parent);
  }

  @Override
  protected Instant parse(String value) {

    return Instant.parse(value);
  }

  @Override
  public ValidatorBuilderInstant<PARENT> past() {

    return add(new ValidatorInstantPast());
  }

  @Override
  public ValidatorBuilderInstant<PARENT> future() {

    return add(new ValidatorInstantFuture());
  }

  @Override
  public ValidatorBuilderInstant<PARENT> after(Instant value) {

    return add(new ValidatorInstantAfter(value));
  }

  @Override
  public ValidatorBuilderInstant<PARENT> after(Supplier<Instant> valueSource) {

    return add(new ValidatorInstantAfter(valueSource));
  }

  @Override
  public ValidatorBuilderInstant<PARENT> before(Instant value) {

    return add(new ValidatorInstantBefore(value));
  }

  @Override
  public ValidatorBuilderInstant<PARENT> before(Supplier<Instant> valueSource) {

    return add(new ValidatorInstantBefore(valueSource));
  }

}
