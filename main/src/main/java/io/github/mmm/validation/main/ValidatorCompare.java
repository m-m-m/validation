/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import java.util.Objects;
import java.util.function.Supplier;

import io.github.mmm.base.compare.CompareOperator;
import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Object, Object) validating} that a value
 * {@link CompareOperator#evalDouble(double, double) satisfies} a given {@link CompareOperator} -operation for given
 * value to compare to.
 *
 * @param <V> is the generic type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public class ValidatorCompare<V extends Comparable<V>> extends AbstractValueValidator<V> {

  /** The comparison operator. */
  private final CompareOperator comparator;

  private final Supplier<V> valueSource;

  private final boolean dynamic;

  /**
   * The constructor.
   *
   * @param comparator is the {@link CompareOperator comparison operator} used to compare the {@link #validate(Object)
   *        value to validate} (first argument) with the value of the given {@code valueSource}.
   * @param valueSource is a reference to something that {@link Supplier#get() provides a value} and will be evaluated
   *        {@link #isDynamic() dynamically} on every {@link #validate(Object) validation}.
   */
  public ValidatorCompare(CompareOperator comparator, Supplier<V> valueSource) {

    super();
    this.comparator = comparator;
    this.valueSource = valueSource;
    this.dynamic = true;
  }

  /**
   * The constructor.
   *
   * @param comparator is the {@link CompareOperator comparison operator} used to compare the {@link #validate(Object)
   *        value to validate} (first argument) with the value of the given {@code value}.
   * @param value is the fixed value to compare to.
   */
  public ValidatorCompare(CompareOperator comparator, V value) {

    super();
    this.comparator = comparator;
    this.valueSource = () -> value;
    this.dynamic = false;
  }

  @Override
  public boolean isDynamic() {

    return this.dynamic;
  }

  @Override
  protected NlsMessage validateNotNull(V value) {

    V value2 = this.valueSource.get();
    if (this.comparator.evalComparable(value, value2)) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorValueComparison(value, this.comparator, value2);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), this.comparator, this.valueSource);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if (!super.equals(obj)) {
      return false;
    }
    ValidatorCompare<?> other = (ValidatorCompare<?>) obj;
    return Objects.equals(this.comparator, other.comparator) && Objects.equals(this.valueSource, other.valueSource);
  }

}
