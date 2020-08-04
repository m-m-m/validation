/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.test;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;

import io.github.mmm.validation.ValidationResult;
import io.github.mmm.validation.ValidationResultValid;
import io.github.mmm.validation.Validator;

/**
 * Abstract base class to tests implementations of {@link io.github.mmm.validation.Validator}.
 */
public abstract class ValidatorTest extends Assertions {

  /**
   * @param <V> type of the values to check.
   * @param validator the {@link Validator} used to validate.
   * @param validValues the valid values to check.
   */
  @SuppressWarnings("unchecked")
  protected static <V> void checkValid(Validator<V> validator, V... validValues) {

    SoftAssertions assertions = new SoftAssertions();
    for (V value : validValues) {
      ValidationResult result = validator.validate(value);
      assertions.assertThat(result.isValid()).as(validator.toString() + "(" + value + ")").isTrue();
      if (result.isValid()) {
        assertions.assertThat(result).isSameAs(ValidationResultValid.get());
      }
    }
    assertions.assertAll();
  }

  /**
   * @param <V> type of the values to check.
   * @param validator the {@link Validator} used to validate.
   * @param rootFailureMessage the expected failure message for the root locale (without any localization).
   * @param invalidValue the invalid value to check.
   */
  protected static <V> void checkInvalid(Validator<V> validator, String rootFailureMessage, V invalidValue) {

    SoftAssertions assertions = new SoftAssertions();
    checkInvalid(assertions, validator, invalidValue, rootFailureMessage);
    assertions.assertAll();
  }

  private static <V> void checkInvalid(SoftAssertions assertions, Validator<V> validator, V invalidValue,
      String rootFailureMessage) {

    ValidationResult result = validator.validate(invalidValue);
    assertions.assertThat(result.isValid()).as(validator.toString() + "(" + invalidValue + ")").isFalse();
    assertions.assertThat(result.getMessage()).isEqualTo(rootFailureMessage);
  }

  /**
   * @param <V> type of the values to check.
   * @param validator the {@link Validator} used to validate.
   * @param rootFailureMessage the expected failure message for the root locale (without any localization).
   * @param invalidValues are the invalid values to check.
   */
  @SuppressWarnings("unchecked")
  protected static <V> void checkInvalid(Validator<V> validator, String rootFailureMessage, V... invalidValues) {

    SoftAssertions assertions = new SoftAssertions();
    for (V value : invalidValues) {
      checkInvalid(assertions, validator, value, rootFailureMessage);
    }
    assertions.assertAll();
  }

}
