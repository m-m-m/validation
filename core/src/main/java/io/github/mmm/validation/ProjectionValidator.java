/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.function.Function;

/**
 * {@link Validator} that projects (maps) the performs the validation using other {@link Validator}(s) on the result.
 *
 * @param <V> type of the value to {@link #validate(Object) validate}.
 * @param <C> type of the child value to validate using the {@link #getChild(int) child validators}.
 *
 * @since 1.0.0
 */
public class ProjectionValidator<V, C> extends AbstractComposedValidator<V, C> {

  private final Function<V, C> function;

  /**
   * The constructor.
   *
   * @param function the projection {@link Function}.
   * @param validators the {@link #getChild(int) child validators}.
   */
  @SuppressWarnings("unchecked")
  public ProjectionValidator(Function<V, C> function, Validator<? super C>... validators) {

    super(validators);
    this.function = function;
  }

  @Override
  public ValidationResult validate(V value, Object valueSource) {

    return validateChild(this.function.apply(value), source2string(valueSource));
  }

}
