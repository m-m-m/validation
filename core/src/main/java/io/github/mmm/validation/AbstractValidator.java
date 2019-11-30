/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.Objects;

/**
 * This is the abstract base class all {@link Validator} implementations should extend.
 *
 * @param <V> is the generic type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public abstract class AbstractValidator<V> implements Validator<V> {

  /**
   * The constructor.
   */
  public AbstractValidator() {

    super();
  }

  /**
   * This is the default implementation to retrieve the {@link ValidationResult#getCode() code} of this
   * {@link Validator}. <br>
   * <b>ATTENTION:</b><br>
   * This default implementation returns the {@link Class#getSimpleName() classname} of the actual {@link Validator}
   * implementation. This strategy is chosen for simplicity when implementing a new validator. To ensure stable codes
   * override this method and return a string constant. This shall at least be done when the name of the class is
   * changed.
   *
   * @return the {@link ValidationResult#getCode() code}.
   */
  protected String getCode() {

    return getClass().getSimpleName();
  }

  /**
   * This method determines if this {@link Validator} is <em>dynamic</em>. Here dynamic means that the validation of the
   * same input may not always return the same validation result (e.g. it holds references to instances that have
   * dynamic impact on the validation).
   *
   * @return {@code true} if this {@link Validator} is dynamic, {@code false} otherwise.
   */
  public boolean isDynamic() {

    return false;
  }

  /**
   * @param validators the {@link AbstractValidator validators} to append.
   * @return a new {@link ComposedValidator} instance composing the current validator ({@code this}) with the given
   *         validator.
   */
  @SuppressWarnings("unchecked")
  public AbstractValidator<V> append(AbstractValidator<? super V>... validators) {

    Objects.requireNonNull(validators, "validators");
    if (validators.length == 0) {
      return this;
    }
    if (validators.length == 1) {
      if (validators[0] == ValidatorNone.getInstance()) {
        return this;
      }
      return new ComposedValidator<>(this, validators[0]);
    }
    AbstractValidator<? super V>[] array = new AbstractValidator[validators.length + 1];
    array[0] = this;
    System.arraycopy(validators, 0, validators, 1, validators.length);
    return new ComposedValidator<>(array);
  }

  /**
   * @param source the {@link #validate(Object, Object) value source}.
   * @return the {@link Object#toString() string representation} of the given {@code source}.
   */
  protected String source2string(Object source) {

    if (source == null) {
      return null;
    }
    return String.valueOf(source);
  }

  @Override
  public int hashCode() {

    return getCode().hashCode();
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if ((obj == null) || (getClass() != obj.getClass())) {
      return false;
    }
    return true;
  }

}
