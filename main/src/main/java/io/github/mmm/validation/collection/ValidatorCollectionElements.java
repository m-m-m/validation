/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.Collection;
import java.util.List;

import io.github.mmm.validation.AbstractComposedValidator;
import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.ValidationResult;
import io.github.mmm.validation.ValidationResultBuilder;
import io.github.mmm.validation.ValidationResultValid;
import io.github.mmm.validation.Validator;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(Collection, Object) validating}
 * {@link Collection#iterator() all elements} of a given {@link Collection} satisfy the given validators.
 *
 * @param <E> the generic type of the {@link Collection#contains(Object) elements contained} in the collection.
 *
 * @since 1.0.0
 */
public class ValidatorCollectionElements<E> extends AbstractComposedValidator<Collection<E>, E> {

  /**
   * The constructor.
   *
   * @param validators the {@link AbstractValidator}s used to {@link AbstractValidator#validate(Object, Object)
   *        validate} each element.
   */
  @SafeVarargs
  public ValidatorCollectionElements(Validator<? super E>... validators) {

    super(validators);
  }

  /**
   * The constructor.
   *
   * @param validators the {@link AbstractValidator}s used to {@link AbstractValidator#validate(Object, Object)
   *        validate} each element.
   */
  public ValidatorCollectionElements(List<AbstractValidator<? super E>> validators) {

    this(validators.toArray(new AbstractValidator[validators.size()]));
  }

  @Override
  public ValidationResult validate(Collection<E> collection, Object valueSource) {

    if (collection == null) {
      return ValidationResultValid.getInstance();
    }
    String source = source2string(valueSource);
    ValidationResultBuilder builder = new ValidationResultBuilder();
    for (E element : collection) {
      validateChild(element, source, builder);
    }
    return builder.build(source);
  }

}
