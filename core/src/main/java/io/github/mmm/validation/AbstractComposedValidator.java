/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.Objects;

import io.github.mmm.base.range.Range;

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

  /** {@link #getChild(int)} */
  protected final Validator<? super C>[] children;

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

    ValidationResultBuilder builder = new ValidationResultBuilder(false);
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

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public <T> Validator<T> append(Validator<?> validator) {

    if ((validator == null) || (validator == Validator.none())) {
      return (Validator<T>) this;
    }
    if (this instanceof ComposedValidator) {
      Validator<?>[] composed = new Validator[this.children.length + 1];
      System.arraycopy(this.children, 0, composed, 0, this.children.length);
      composed[this.children.length] = validator;
      return new ComposedValidator(composed);
    } else {
      return new ComposedValidator(this, validator);
    }
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public <T> Validator<T> append(Validator<?>... validators) {

    if ((validators == null) || (validators.length == 0)) {
      return (Validator<T>) this;
    }
    if (validators.length == 1) {
      if (validators[0] == Validator.none()) {
        return (Validator<T>) this;
      }
    }
    if (this instanceof ComposedValidator) {
      Validator<?>[] composed = new Validator[this.children.length + validators.length];
      System.arraycopy(this.children, 0, composed, 0, this.children.length);
      System.arraycopy(validators, 0, composed, this.children.length, validators.length);
      return new ComposedValidator(composed);
    } else {
      return super.append(validators);
    }
  }

  @Override
  public boolean containsId(String id) {

    if (super.containsId(id)) {
      return true;
    }
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      Validator<? super C> child = getChild(i);
      if (child.containsId(id)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public <T> T getChild(Class<T> validatorClass) {

    T validator = super.getChild(validatorClass);
    if (validator == null) {
      int childCount = getChildCount();
      for (int i = 0; i < childCount; i++) {
        Validator<? super C> child = getChild(i);
        validator = child.getChild(validatorClass);
        if (validator != null) {
          break;
        }
      }
    }
    return validator;
  }

  @Override
  @SuppressWarnings({ "rawtypes" })
  public <T extends Comparable> Range<T> getRange() {

    Range<T> range = Range.unbounded();
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      Validator<? super C> child = getChild(i);
      Range<T> range2 = child.getRange();
      range = range.intersection(range2);
    }
    return range;
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), this.children);
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
