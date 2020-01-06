/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import io.github.mmm.base.i18n.Localizable;

/**
 * This is the abstract base implementation of {@link io.github.mmm.validation.Validator}.
 *
 * @param <V> type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public abstract class AbstractValueValidator<V> extends AbstractValidator<V> {

  /**
   * The constructor.
   */
  public AbstractValueValidator() {

    super();
  }

  @Override
  public ValidationResult validate(V value, Object valueSource) {

    Localizable failureMessage;
    if (value == null) {
      failureMessage = validateNull();
    } else {
      failureMessage = validateNotNull(value);
    }
    ValidationResult result = ValidationResultValid.get();
    if (failureMessage != null) {
      result = new ValidationFailure(getCode(), failureMessage, source2string(valueSource));
    }
    return result;
  }

  /**
   * This method performs the validation in case {@code null} was provided as value. By default {@code null} should be
   * considered as a legal value. Only for validators such as {@link Validator#isMandatory() mandatory validator} this
   * method should be overridden.
   *
   * @return the {@link ValidationResult#getMessage() failure message} or {@code null} if {@code null} is valid.
   */
  protected Localizable validateNull() {

    return null;
  }

  /**
   * This method performs the validation in case {@code value} is NOT {@code null}. This method contains the actual
   * custom logic for the validation. It is therefore designed in a way that makes it most simple to implement custom
   * validators.
   *
   * @param value the value to validate.
   * @return the {@link ValidationResult#getMessage() failure message} or {@code null} if the the given {@code value} is
   *         valid.
   */
  protected abstract Localizable validateNotNull(V value);

}
