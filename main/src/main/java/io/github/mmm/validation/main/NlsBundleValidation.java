/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import io.github.mmm.nls.NlsBundle;
import io.github.mmm.nls.NlsMessage;
import io.github.mmm.nls.argument.NlsArguments;

/**
 * {@link NlsBundle} for this module.
 *
 * @since 1.0.0
 */
public class NlsBundleValidation extends NlsBundle {

  /** The singleton instance. */
  public static final NlsBundleValidation INSTANCE = new NlsBundleValidation();

  /**
   * The constructor.
   */
  private NlsBundleValidation() {

    super();
  }

  /**
   * @param value is the value that does NOT match the expected format.
   * @param format is the expected format.
   * @return the {@link NlsMessage}
   */
  public NlsMessage errorValueFormat(Object value, Object format) {

    return create("errorValueFormat", "The value has to match the format '{format}'.", NlsArguments.ofFormat(format));
  }

  /**
   * @return the {@link NlsMessage}.
   */
  public NlsMessage errorMandatory() {

    return create("errorMandatory", "The value has to be filled.", NlsArguments.of());
  }

  /**
   * @return the {@link NlsMessage}.
   */
  public NlsMessage errorPasswordMismatch() {

    return create("errorPasswordMismatch", "The passwords have to match.", NlsArguments.of());
  }

  /**
   * @see io.github.mmm.validation.main.ValidatorCompare
   *
   * @param value is the invalid value.
   * @param operation is the {@link io.github.mmm.base.compare.CompareOperator}.
   * @param value2 is the value to compare to (second argument).
   * @return the {@link NlsMessage}
   */
  public NlsMessage errorValueComparison(Object value, Object operation, Object value2) {

    return create("errorValueComparison", "The value needs to be {comparator} '{value}'.",
        NlsArguments.of(KEY_OPERATION, operation, KEY_VALUE, value2));
  }

  /**
   * @param value is the invalid value.
   * @return the {@link NlsMessage}.
   */
  public NlsMessage errorValueNotInPast(Object value) {

    return create("errorValueNotInPast", "The value has to be in the past.", NlsArguments.of());
  }

  /**
   * @param value is the invalid value.
   * @return the {@link NlsMessage}.
   */
  public NlsMessage errorValueNotInFuture(Object value) {

    return create("errorValueNotInFuture", "The value has to be in the future.", NlsArguments.of());
  }

  /**
   * @param value is the invalid value.
   * @param min the boundary that {@code value} should be after.
   * @return the {@link NlsMessage}.
   */
  public NlsMessage errorValueNotAfter(Object value, Object min) {

    return create("errorValueNotAfter", "The value has to be after '{value}'.", NlsArguments.ofValue(min));
  }

  /**
   * @param value is the invalid value.
   * @param max the lower bound.
   * @return the {@link NlsMessage}.
   */
  public NlsMessage errorValueNotBefore(Object value, Object max) {

    return create("errorValueNotAfter", "The value has to be before '{value}'.", NlsArguments.ofValue(max));
  }

  /**
   * @param value is the invalid value.
   * @param min is the minimum value.
   * @param max is the maximum value.
   * @return the {@link NlsMessage}
   */
  public NlsMessage errorValueOutOfRange(Object value, Object min, Object max) {

    return create("errorValueOutOfRange", "The value needs to be in the range from {min} to {max}.",
        NlsArguments.ofMinMax(min, max));
  }

}
