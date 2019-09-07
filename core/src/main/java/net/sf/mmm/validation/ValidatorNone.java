/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.validation;

/**
 * {@link Validator} that always validates successfully.
 *
 * @since 1.0.0
 */
public final class ValidatorNone extends AbstractValidator<Object> {

  private static final ValidatorNone INSTANCE = new ValidatorNone();

  /**
   * The constructor.
   */
  public ValidatorNone() {

    super();
  }

  /**
   * @param <VALUE> is the generic type of the value to validate.
   * @return the instance of this validator that always validates successfully.
   */
  @SuppressWarnings({ "unchecked" })
  public static <VALUE> AbstractValidator<VALUE> getInstance() {

    return (AbstractValidator<VALUE>) INSTANCE;
  }

  @Override
  public ValidationResult validate(Object value, Object valueSource) {

    return ValidationResultValid.getInstance();
  }

  @SuppressWarnings("unchecked")
  @Override
  public AbstractValidator<Object> append(AbstractValidator<? super Object>... validators) {

    return new ComposedValidator<>(validators);
  }

  @Override
  public int hashCode() {

    return 0;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    return true;
  }

}
