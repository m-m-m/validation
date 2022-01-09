package io.github.mmm.validation;

import io.github.mmm.base.exception.ApplicationException;

/**
 * {@link ApplicationException} if a validation failed.
 */
public class ValidationFailedException extends ApplicationException {

  private static final long serialVersionUID = 1L;

  private final ValidationResult result;

  /**
   * The constructor.
   *
   * @param result the {@link #getResult() validation result}.
   */
  public ValidationFailedException(ValidationResult result) {

    super(result);
    assert (!result.isValid());
    this.result = result;
  }

  /**
   * @return the {@link ValidationResult}. Shall never be {@link ValidationResult#isValid() valid}.
   */
  public ValidationResult getResult() {

    return this.result;
  }

  @Override
  public String getCode() {

    return "VAL";
  }

  @Override
  public boolean isTechnical() {

    return false;
  }

}
