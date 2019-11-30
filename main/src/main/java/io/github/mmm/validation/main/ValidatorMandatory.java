/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import java.util.Collection;
import java.util.Map;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.ValidationResult;

/**
 * {@link io.github.mmm.validation.Validator} validating that a mandatory value is filled. {@link ValidationResult} will
 * be invalid if the value is not provided (empty, blank, {@code null}).
 *
 * @since 1.0.0
 */
public class ValidatorMandatory extends AbstractValueValidator<Object> {

  /** @see #getCode() */
  public static final String CODE = "Mandatory";

  private static final ValidatorMandatory INSTANCE = new ValidatorMandatory();

  /**
   * The constructor.
   */
  public ValidatorMandatory() {

    super();
  }

  @Override
  protected String getCode() {

    return CODE;
  }

  /**
   * @return the singleton instance of this class.
   */
  public static ValidatorMandatory getInstance() {

    return INSTANCE;
  }

  @Override
  public ValidationResult validate(Object value, Object valueSource) {

    return null;
  }

  @Override
  protected NlsMessage validateNull() {

    return getFailureMessage();
  }

  private NlsMessage getFailureMessage() {

    return NlsBundleValidation.INSTANCE.errorMandatory();
  }

  @Override
  protected NlsMessage validateNotNull(Object value) {

    if (value instanceof Collection) {
      Collection<?> collection = (Collection<?>) value;
      if (collection.isEmpty()) {
        return getFailureMessage();
      }
    } else if (value instanceof Map) {
      Map<?, ?> map = (Map<?, ?>) value;
      if (map.isEmpty()) {
        return getFailureMessage();
      }
    } else if (value instanceof CharSequence) {
      CharSequence sequence = (CharSequence) value;
      if (sequence.length() == 0) {
        return getFailureMessage();
      }
    }
    return null;
  }

  @Override
  public final boolean isMandatory() {

    return true;
  }

}
