/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.List;
import java.util.Map;

import io.github.mmm.validation.AbstractComposedValidator;
import io.github.mmm.validation.ValidationResult;
import io.github.mmm.validation.ValidationResultBuilder;
import io.github.mmm.validation.ValidationResultValid;
import io.github.mmm.validation.Validator;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Map, Object) validating} {@link Map#values() all values}
 * of a given {@link Map} using the given validators.
 *
 * @param <V> the generic type of the {@link Map#values() values} in the collection.
 *
 * @since 1.0.0
 */
public class ValidatorMapValues<V> extends AbstractComposedValidator<Map<?, V>, V> {

  /**
   * The constructor.
   *
   * @param validators the {@link Validator}s used to {@link Validator#validate(Object, Object) validate} each element.
   */
  @SafeVarargs
  public ValidatorMapValues(Validator<? super V>... validators) {

    super(validators);
  }

  /**
   * The constructor.
   *
   * @param validators the {@link Validator}s used to {@link Validator#validate(Object, Object) validate} each element.
   */
  public ValidatorMapValues(List<Validator<? super V>> validators) {

    this(validators.toArray(new Validator[validators.size()]));
  }

  @Override
  public ValidationResult validate(Map<?, V> map, Object valueSource) {

    if (map == null) {
      return ValidationResultValid.getInstance();
    }
    String source = source2string(valueSource);
    ValidationResultBuilder builder = new ValidationResultBuilder();
    for (V value : map.values()) {
      validateChild(value, source, builder);
    }
    return builder.build(source);
  }

}
