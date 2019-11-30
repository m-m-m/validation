/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.main;

import io.github.mmm.validation.AbstractValidator;

/**
 * {@link ObjectValidatorBuilder Validator builder} for container values such as {@link java.util.Collection}.
 *
 * @param <V> the generic type of the value to {@link AbstractValidator#validate(Object) validate}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 * @param <SELF> the generic type of this builder itself (this).
 *
 * @since 1.0.0
 */
public abstract class ContainerValidatorBuilder<V, PARENT, SELF extends ContainerValidatorBuilder<V, PARENT, SELF>>
    extends ObjectValidatorBuilder<V, PARENT, SELF> {

  private ObjectValidatorBuilderFactory<SELF> subFactory;

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public ContainerValidatorBuilder(PARENT parent) {

    super(parent);
  }

  /**
   * @return the sub-{@link ObjectValidatorBuilderFactory factory}.
   */
  public ObjectValidatorBuilderFactory<SELF> getSubFactory() {

    if (this.subFactory == null) {
      this.subFactory = new ObjectValidatorBuilderFactory<>(self());
    }
    return this.subFactory;
  }

}
