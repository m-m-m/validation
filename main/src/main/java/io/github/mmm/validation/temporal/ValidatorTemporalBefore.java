/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.temporal;

import java.util.Objects;
import java.util.function.Supplier;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link io.github.mmm.validation.Validator#validate(Object, Object)
 * validating} that a {@link java.time.temporal.Temporal} lies before a given value.
 *
 * @param <V> is the generic type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public abstract class ValidatorTemporalBefore<V> extends AbstractValueValidator<V> {

  /** @see #getId() */
  public static final String ID = "Before";

  private final Supplier<V> boundSource;

  private final V bound;

  /**
   * The constructor.
   *
   * @param valueSource the {@link Supplier} of the value to compare to.
   */
  public ValidatorTemporalBefore(Supplier<V> valueSource) {

    super();
    this.boundSource = valueSource;
    this.bound = null;
  }

  /**
   * The constructor.
   *
   * @param value the value to compare to.
   */
  public ValidatorTemporalBefore(V value) {

    super();
    this.boundSource = null;
    this.bound = value;
  }

  @Override
  public boolean isDynamic() {

    return (this.boundSource != null);
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(V value) {

    V limit;
    if (this.boundSource == null) {
      limit = this.bound;
    } else {
      limit = this.boundSource.get();
    }
    if (isBefore(value, limit)) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorValueNotBefore(value, limit);
  }

  /**
   * @param value the date to check.
   * @param limit the upper bound to compare to.
   * @return {@code true} if {@code value} is before {@code limit}, {@code false} otherwise.
   */
  protected abstract boolean isBefore(V value, V limit);

  @Override
  public Object getMax() {

    return this.bound;
  }

  private Object getSource() {

    if (this.boundSource == null) {
      return this.bound;
    } else {
      return this.boundSource;
    }
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), getSource());
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if (!super.equals(obj)) {
      return false;
    }
    ValidatorTemporalBefore<?> other = (ValidatorTemporalBefore<?>) obj;
    return Objects.equals(getSource(), other.getSource());
  }
}
