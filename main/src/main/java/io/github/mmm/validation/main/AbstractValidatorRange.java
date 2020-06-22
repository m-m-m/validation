/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import java.util.Objects;

import io.github.mmm.base.compare.CompareOperator;
import io.github.mmm.base.range.Range;
import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Object, Object) validating} that a given value
 * {@link Range#contains(Object) is contained} in a given {@link Range}.
 *
 * @param <V> the generic type of the value to {@link #validate(Object) validate}.
 * @param <R> the generic type of the {@link Range}-bounds.
 *
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class AbstractValidatorRange<V, R> extends AbstractValueValidator<V> {

  /** @see #getCode() */
  public static final String CODE = "Range";

  /** @see #getRange() */
  protected final Range<R> range;

  /**
   * The constructor.
   *
   * @param range is the {@link Range} the value has to be {@link Range#contains(Object) contained in}.
   */
  public AbstractValidatorRange(Range<R> range) {

    super();
    this.range = range;
  }

  @Override
  protected String getCode() {

    return CODE;
  }

  /**
   * @return the {@link Range} to validate.
   */
  public Range<R> getRange() {

    return this.range;
  }

  /**
   * Converts the value to the type of the range.
   *
   * @param value is the value to convert.
   * @return the converted value.
   */
  @SuppressWarnings("unchecked")
  protected R convertValue(V value) {

    return (R) value;
  }

  @Override
  protected NlsMessage validateNotNull(V value) {

    R convertedValue = convertValue(value);
    if (this.range.contains(convertedValue)) {
      return null;
    } else {
      R min = this.range.getMin();
      R max = this.range.getMax();
      R bound = null;
      CompareOperator operation = null;
      if (min == null) {
        operation = CompareOperator.LESS_OR_EQUAL;
        bound = max;
      } else if (max == null) {
        operation = CompareOperator.GREATER_OR_EQUAL;
        bound = min;
      }
      if (isLength()) {
        if (operation == null) {
          return NlsBundleValidation.INSTANCE.errorLengthOutOfRange(convertedValue, min, max);
        } else {
          return NlsBundleValidation.INSTANCE.errorLengthComparison(convertedValue, operation, bound);
        }
      } else {
        if (operation == null) {
          return NlsBundleValidation.INSTANCE.errorValueOutOfRange(convertedValue, min, max);
        } else {
          return NlsBundleValidation.INSTANCE.errorValueComparison(convertedValue, operation, bound);
        }
      }
    }
  }

  /**
   * @return {@code true} if validating the lenght of the value, {@code false} otherwise.
   */
  protected boolean isLength() {

    return false;
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), this.range);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if (!super.equals(obj)) {
      return false;
    }
    AbstractValidatorRange other = (AbstractValidatorRange) obj;
    return Objects.equals(this.range, other.range);
  }

}
