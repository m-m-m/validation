/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.time;

import java.util.function.Supplier;

import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.main.ComparableValidatorBuilder;
import io.github.mmm.validation.main.ObjectValidatorBuilder;

/**
 * {@link ObjectValidatorBuilder Validator builder} for date and time values.
 *
 * @param <V> the generic type of the value to {@link AbstractValidator#validate(Object) validate}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 * @param <SELF> the generic type of this builder itself (this).
 *
 * @since 1.0.0
 */
public abstract class ValidatorBuilderTemporal<V extends Comparable<?>, PARENT, SELF extends ValidatorBuilderTemporal<V, PARENT, SELF>>
    extends ComparableValidatorBuilder<V, PARENT, SELF> {

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ValidatorBuilderTemporal(PARENT parent) {

    super(parent);
  }

  /**
   * {@link #add(AbstractValidator) Adds} a validator that checks that the date/time is in the past.
   *
   * @return this build instance for fluent API calls.
   */
  public abstract SELF past();

  /**
   * {@link #add(AbstractValidator) Adds} a validator that checks that the date/time is in the future.
   *
   * @return this build instance for fluent API calls.
   */
  public abstract SELF future();

  /**
   * {@link #add(AbstractValidator) Adds} a validator that checks that the date/time is after the given {@code value}.
   *
   * @param value the date/time to compare.
   * @return this build instance for fluent API calls.
   */
  public abstract SELF after(V value);

  /**
   * {@link #add(AbstractValidator) Adds} a validator that checks that the date/time is after the given {@code value}.
   *
   * @param valueSource the {@link Supplier} of the date/time to compare.
   * @return this build instance for fluent API calls.
   */
  public abstract SELF after(Supplier<V> valueSource);

  /**
   * {@link #add(AbstractValidator) Adds} a validator that checks that the date/time is before the given {@code value}.
   *
   * @param value the date/time to compare.
   * @return this build instance for fluent API calls.
   */
  public abstract SELF before(V value);

  /**
   * {@link #add(AbstractValidator) Adds} a validator that checks that the date/time is before the given {@code value}.
   *
   * @param valueSource the {@link Supplier} of the date/time to compare.
   * @return this build instance for fluent API calls.
   */
  public abstract SELF before(Supplier<V> valueSource);

}
