/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import java.util.Objects;

import io.github.mmm.base.range.Range;
import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Object, Object) validating} that a given value
 * {@link Range#isContained(Object) is contained} in a given {@link Range}.
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

  private final Range<R> range;

  /**
   * The constructor.
   *
   * @param range is the {@link Range} the value has to be {@link Range#isContained(Object) contained in}.
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
    if (this.range.isContained(convertedValue)) {
      return null;
    } else {
      return NlsBundleValidation.INSTANCE.errorValueOutOfRange(convertedValue, this.range.getMin(),
          this.range.getMax());
    }
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
