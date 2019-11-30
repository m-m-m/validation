/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

/**
 * This is the interface for a registry where {@link Validator}s can be {@link #add(Validator) added}.
 *
 * @param <V> the generic type of the value to {@link Validator#validate(Object) validate}. May be {@link Object} to
 *        register any validator. Otherwise only validators can be {@link #add(Validator) added} that are compatible
 *        with this type.
 * @param <SELF> the result of the {@link #add(Validator)} method. Typically the self reference (this) for fluent API
 *        calls.
 *
 * @since 1.0.0
 */
public interface ValidatorRegistry<V, SELF> {

  /**
   * @param validator the {@link AbstractValidator} to add to this builder.
   * @return this build instance for fluent API calls.
   */
  SELF add(Validator<? super V> validator);

}
