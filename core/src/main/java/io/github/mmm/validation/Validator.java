/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import io.github.mmm.base.lang.Composable;

/**
 * A {@link Validator} allows to {@link #validate(Object) validate} according values. <br>
 * There can be arbitrary implementations of this interface. A regular implementation shall be stateless and therefore
 * thread-safe. All parameterization shall therefore happen on initialization - ideally at construction. <br>
 * <b>NOTE:</b><br>
 * This API intentionally does NOT make use of {@link Throwable exceptions} as they are more expensive to produce and
 * shall only occur in exceptional situations, while a validation failure is a regular use-case. Further, a validation
 * shall validate entire objects to the end collecting all {@link ValidationResult failures} so the end-user can see and
 * fix all problems at once.<br>
 * <b>ATTENTION:</b><br>
 * The {@code null} values is typically only handled by {@link #isMandatory() mandatory validator}. Other validators
 * will treat {@code null} as a valid value. This design gives the best flexibility as it allows to define specific
 * constraints also for optional values. However, you need to be aware this fact to avoid mistakes. So e.g. adding a
 * validator requiring that the minimum size/length of a value needs to be e.g. 2 will still accept {@code null} as
 * valid. So in such case you most probably want to {@link ComposedValidator combine} it with the {@link #isMandatory()
 * mandatory validator}.
 *
 * @param <V> type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public interface Validator<V> extends Composable<Validator<?>> {

  /**
   * This method validates the given {@code value}.
   *
   * @param value is the value to validate.
   * @return the {@link ValidationResult} or {@code null} if the given {@code value} is valid according to this
   *         {@link Validator}.
   */
  default ValidationResult validate(V value) {

    return validate(value, null);
  }

  /**
   * This method validates the given {@code value}.
   *
   * @param value is the value to validate.
   * @param valueSource is the {@link ValidationResult#getSource() source} describing the origin of the given
   *        {@code value}. The source needs to have a reasonable {@link Object#toString() string-representation} as this
   *        may be displayed to the end-user to locate the source of the failure. In most cases it is suitable to
   *        directly pass a {@link String}.
   * @return the {@link ValidationResult}.
   */
  ValidationResult validate(V value, Object valueSource);

  /**
   * @return {@code true} if this is a validator for mandatory fields (that will not accept {@code null} or empty
   *         values), {@code false} otherwise.
   */
  default boolean isMandatory() {

    return false;
  }

  /**
   * @param validators the {@link Validator}s to append.
   * @return a new {@link Validator} instance composing this {@link Validator} with the given {@code validators}.
   */
  @SuppressWarnings("unchecked")
  default Validator<V> append(Validator<? super V>... validators) {

    if ((validators == null) || (validators.length == 0)) {
      return this;
    }
    Validator<? super V>[] children = new Validator[validators.length + 1];
    children[0] = this;
    System.arraycopy(validators, 0, children, 1, validators.length);
    return new ComposedValidator<>(validators);
  }

}
