/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import io.github.mmm.base.lang.Composable;
import io.github.mmm.base.range.Range;
import io.github.mmm.validation.impl.ValidatorNone;

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
   * @see #isMandatory()
   * @see #getId()
   */
  String ID_MANDATORY = "Mandatory";

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
   * @return the identifier of this {@link Validator}.
   * @see ValidationResult#getCode()
   */
  String getId();

  /**
   * @return {@code true} if this is a validator for mandatory fields (that will not accept {@code null} or empty
   *         values), {@code false} otherwise.
   */
  default boolean isMandatory() {

    return containsId(ID_MANDATORY);
  }

  /**
   * @param id the {@link #getId() ID} of the {@link Validator} to check for.
   * @return {@code true} if this {@link Validator} itself {@link #getId() has} the given ID or recursively
   *         {@link ComposedValidator#getChild(int) contains} such {@link Validator}, {@code false} otherwise.
   */
  default boolean containsId(String id) {

    return (getId().equals(id));
  }

  /**
   * @param <T> type of the requested {@link Validator}.
   * @param validatorClass the {@link Class} reflecting the requested {@link Validator}.
   * @return the requested {@link Validator} or {@code null} if this validator is not of the given type and does not
   *         contain any child (recursively) of the given type.
   */
  default <T> T getChild(Class<T> validatorClass) {

    if (getClass().equals(validatorClass)) {
      return validatorClass.cast(this);
    }
    return null;
  }

  /**
   * @param <T> type of the {@link Range} value. Typically of type {@literal <V>} but e.g. for {@link String} or
   *        {@link java.util.Collection} the type would be {@link Integer} to validate the size of the actual value.
   * @return the {@link Range} constraint with an optional {@link #getMin() mimimum} and/or {@link #getMax() maximum}
   *         value. Will be {@link Range#unbounded()} by if no bounds are validated.
   */
  default <T extends Comparable<?>> Range<T> getRange() {

    return Range.unbounded();
  }

  /**
   * @param <T> type of the minimum value. Typically of type {@literal <V>} but e.g. for {@link String} or
   *        {@link java.util.Collection} the type would be {@link Integer} to validate the size of the actual value.
   * @return the minimum allowed value.
   */
  @SuppressWarnings("unchecked")
  default <T extends Comparable<?>> T getMin() {

    return (T) getRange().getMin();
  }

  /**
   * @param <T> type of the minimum value. Typically of type {@literal <V>} but e.g. for {@link String} or
   *        {@link java.util.Collection} the type would be {@link Integer} to validate the size of the actual value.
   * @return the maximum allowed value.
   */
  @SuppressWarnings("unchecked")
  default <T extends Comparable<?>> T getMax() {

    return (T) getRange().getMax();
  }

  /**
   * @param <T> type of the value to {@link #validate(Object) validate}.
   * @param validator the {@link Validator} to append.
   * @return a new {@link Validator} instance composing this {@link Validator} with the given {@link Validator}.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  default <T> Validator<T> append(Validator<?> validator) {

    if ((validator == null) || (validator == Validator.none())) {
      return (Validator<T>) this;
    }
    return new ComposedValidator(this, validator);
  }

  /**
   * @param <T> type of the value to {@link #validate(Object) validate}.
   * @param validators the {@link Validator}s to append.
   * @return a new {@link Validator} instance composing this {@link Validator} with the given {@link Validator}s.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  default <T> Validator<T> append(Validator<?>... validators) {

    if ((validators == null) || (validators.length == 0)) {
      return (Validator<T>) this;
    }
    if (validators.length == 1) {
      if (validators[0] == Validator.none()) {
        return (Validator<T>) this;
      }
      return new ComposedValidator(this, validators[0]);
    }
    Validator<?>[] children = new Validator[validators.length + 1];
    children[0] = this;
    System.arraycopy(validators, 0, children, 1, validators.length);
    return new ComposedValidator(validators);
  }

  /**
   * @param <T> type of the value to {@link #validate(Object) validate}.
   * @return an instance of {@link Validator} that always return {@link ValidationResultValid} (accepts any value as
   *         valid input).
   */
  @SuppressWarnings({ "unchecked" })
  static <T> Validator<T> none() {

    return (Validator<T>) ValidatorNone.INSTANCE;
  }

  /**
   * @param validator the {@link Validator} to check. May be {@code null}.
   * @return {@code true} if the given {@link Validator} is neither {@code null} nor {@link #none() none}, {@code false}
   *         otherwise.
   */
  static boolean isValidating(Validator<?> validator) {

    if (validator == null) {
      return false;
    }
    if (validator == ValidatorNone.INSTANCE) {
      return false;
    }
    return true;
  }

}
