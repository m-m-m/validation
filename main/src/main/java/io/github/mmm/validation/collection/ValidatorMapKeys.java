/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.List;
import java.util.Map;

import io.github.mmm.validation.AbstractComposedValidator;
import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.ValidationResult;
import io.github.mmm.validation.ValidationResultBuilder;
import io.github.mmm.validation.ValidationResultValid;
import io.github.mmm.validation.Validator;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Map, Object) validating} {@link Map#keySet() all keys} of
 * a given {@link Map} using the {@link #ValidatorMapKeys(AbstractValidator...) given} validators.
 *
 * @param <K> the generic type of the {@link Map#keySet() keys} in the collection.
 *
 * @since 1.0.0
 */
public class ValidatorMapKeys<K> extends AbstractComposedValidator<Map<K, ?>, K> {

  /**
   * The constructor.
   *
   * @param validators the {@link AbstractValidator}s used to {@link AbstractValidator#validate(Object, Object)
   *        validate} each element.
   */
  @SafeVarargs
  public ValidatorMapKeys(Validator<? super K>... validators) {

    super(validators);
  }

  /**
   * The constructor.
   *
   * @param validators the {@link AbstractValidator}s used to {@link AbstractValidator#validate(Object, Object)
   *        validate} each element.
   */
  public ValidatorMapKeys(List<Validator<? super K>> validators) {

    this(validators.toArray(new Validator[validators.size()]));
  }

  @Override
  public ValidationResult validate(Map<K, ?> map, Object valueSource) {

    if (map == null) {
      return ValidationResultValid.getInstance();
    }
    String source = source2string(valueSource);
    ValidationResultBuilder builder = new ValidationResultBuilder();
    for (K key : map.keySet()) {
      validateChild(key, source, builder);
    }
    return builder.build(source);
  }

}
