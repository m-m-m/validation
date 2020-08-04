/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

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
   * {@link AbstractValidator} only provides a default implementation of this method.<br>
   * <b>ATTENTION:</b><br>
   * This default implementation returns the {@link Class#getSimpleName() simple class name} of the actual
   * {@link Validator} implementation. This strategy is chosen for simplicity when implementing a new {@link Validator}.
   * To ensure stable IDs override this method and return a string constant. This shall at least be done when the name
   * of the class is changed to provide backwards compatibility.
   */
  @Override
  public String getId() {

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

    return getId().hashCode();
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

  @Override
  public String toString() {

    return getId();
  }

}
