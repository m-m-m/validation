/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

/**
 * {@link Validator} that is composed out of a set of individual {@link #getChild(int) validators} given at
 * {@link #ComposedValidator(Validator...) construction}. It will always invoke <em>all</em> {@link #getChild(int)
 * contained validators} when a {@link #validate(Object, Object) validation} is performed.
 *
 * @param <V> is the generic type of the value to {@link #validate(Object) validate}.
 *
 * @since 1.0.0
 */
public class ComposedValidator<V> extends AbstractComposedValidator<V, V> {

  /** @see #getId() */
  public static final String ID = "ComposedValidator";

  /**
   * The constructor.
   *
   * @param validators are the {@link #getChild(int) child} {@link Validator}s to compose.
   */
  @SafeVarargs
  public ComposedValidator(Validator<? super V>... validators) {

    super(validators);
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  public ValidationResult validate(V value, Object valueSource) {

    return validateChild(value, source2string(valueSource));
  }

  @Override
  public Object getMin() {

    for (Validator<? super V> child : this.children) {
      Object min = child.getMin();
      if (min != null) {
        return min;
      }
    }
    return super.getMin();
  }

  @Override
  public Object getMax() {

    for (Validator<? super V> child : this.children) {
      Object max = child.getMax();
      if (max != null) {
        return max;
      }
    }
    return super.getMax();
  }

}
