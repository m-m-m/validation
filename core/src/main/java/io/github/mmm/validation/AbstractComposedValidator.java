/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.Objects;

/**
 * {@link Validator} that is composed out of a set of individual {@link #getChild(int) validators} given at
 * {@link #AbstractComposedValidator(Validator...) construction}. It will always invoke <em>all</em>
 * {@link #getChild(int) contained validators} when a {@link #validate(Object, Object) validation} is performed.
 *
 * @param <V> type of the value to {@link #validate(Object) validate}.
 * @param <C> type of the child value to validate using the {@link #getChild(int) child validators}.
 *
 * @since 1.0.0
 */
public abstract class AbstractComposedValidator<V, C> extends AbstractValidator<V> {

  private final Validator<? super C>[] children;

  /**
   * The constructor.
   *
   * @param validators are the {@link #getChild(int) child} {@link Validator}s to compose.
   */
  @SafeVarargs
  public AbstractComposedValidator(Validator<? super C>... validators) {

    super();
    this.children = validators;
  }

  /**
   * @param value is the value to validate.
   * @param valueSource is the {@link ValidationResult#getSource() source}.
   * @return the {@link ValidationResult}.
   * @see #validate(Object, Object)
   */
  protected ValidationResult validateChild(C value, String valueSource) {

    ValidationResultBuilder builder = new ValidationResultBuilder();
    validateChild(value, valueSource, builder);
    return builder.build(valueSource);
  }

  /**
   * @param value is the value to validate.
   * @param valueSource is the {@link ValidationResult#getSource() source}.
   * @param builder the {@link ValidationResultBuilder}.
   * @see #validate(Object, Object)
   */
  protected void validateChild(C value, String valueSource, ValidationResultBuilder builder) {

    for (Validator<? super C> child : this.children) {
      ValidationResult failure = child.validate(value, valueSource);
      builder.add(failure);
    }
  }

  @Override
  public int getChildCount() {

    return this.children.length;
  }

  @Override
  public Validator<? super C> getChild(int index) {

    return this.children[index];
  }

  @SuppressWarnings("unchecked")
  @Override
  public AbstractValidator<V> append(AbstractValidator<? super V>... validators) {

    if ((validators == null) || (validators.length == 0)) {
      return this;
    }
    if (this instanceof ComposedValidator) {
      Validator<? super V>[] composed = new Validator[this.children.length + validators.length];
      System.arraycopy(this.children, 0, composed, 0, this.children.length);
      System.arraycopy(validators, 0, composed, this.children.length, validators.length);
      return new ComposedValidator<>(composed);
    } else {
      return super.append(validators);
    }
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if (!super.equals(obj)) {
      return false;
    }
    AbstractComposedValidator<?, ?> other = (AbstractComposedValidator<?, ?>) obj;
    return Objects.equals(this.children, other.children);
  }
}
