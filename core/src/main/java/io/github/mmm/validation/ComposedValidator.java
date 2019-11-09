/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.Objects;

/**
 * This is an implementation of {@link Validator} that is composed out of a set of individual {@link #getChild(int)
 * validators} given at {@link #ComposedValidator(AbstractValidator...) construction}. It will always invoke
 * <em>all</em> {@link #getChild(int) contained validators} when a {@link #validate(Object, Object) validation} is
 * performed.
 *
 * @param <V> is the generic type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public class ComposedValidator<V> extends AbstractValidator<V> {

  /** @see #getCode() */
  public static final String CODE = "ComposedValidator";

  /** The child validators. */
  private final Validator<? super V>[] children;

  /**
   * The constructor.
   *
   * @param validators are the {@link #getChild(int) child} {@link Validator}s to compose.
   */
  @SafeVarargs
  public ComposedValidator(Validator<? super V>... validators) {

    super();
    this.children = validators;
  }

  @Override
  protected String getCode() {

    return CODE;
  }

  @Override
  public ValidationResult validate(V value, Object valueSource) {

    String source = null;
    if (valueSource != null) {
      source = valueSource.toString();
    }
    ValidationResultBuilder builder = new ValidationResultBuilder();
    for (Validator<? super V> child : this.children) {
      ValidationResult failure = child.validate(value, source);
      builder.add(failure);
    }
    return builder.build(source);
  }

  @Override
  public int getChildCount() {

    return this.children.length;
  }

  @Override
  public Validator<? super V> getChild(int index) {

    return this.children[index];
  }

  @SuppressWarnings("unchecked")
  @Override
  public ComposedValidator<V> append(AbstractValidator<? super V>... validators) {

    if ((validators == null) || (validators.length == 0)) {
      return this;
    }
    Validator<? super V>[] composed = new Validator[this.children.length + validators.length];
    System.arraycopy(this.children, 0, composed, 0, this.children.length);
    System.arraycopy(validators, 0, composed, this.children.length, validators.length);
    return new ComposedValidator<>(composed);
  }

  @Override
  public int hashCode() {

    return 97531;
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
    ComposedValidator<?> other = (ComposedValidator<?>) obj;
    if (!Objects.equals(this.children, other.children)) {
      return false;
    }
    return true;
  }
}
