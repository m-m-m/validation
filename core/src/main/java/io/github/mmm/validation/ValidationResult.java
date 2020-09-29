/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.Locale;

import io.github.mmm.base.i18n.Localizable;
import io.github.mmm.base.lang.Composable;

/**
 * Result of a {@link Validator#validate(Object) validation}. If {@link #isValid() valid} is {@code true}, the
 * {@link #getMessage() message} is {@code null}. Otherwise the message shall be filled and explain the reason of the
 * failure in a understandable but short form to end-users. Examples are "Value may not be blank." or "Value has to be
 * in the range from 5 to 9.". Depending on the {@link Validator#validate(Object, Object) usage} the message may also
 * contain additional context information in order to determine the source of the problem.
 *
 * @since 1.0.0
 */
public interface ValidationResult extends Composable<ValidationResult>, Localizable {

  /**
   * @return {@code true} if the validation was successful, {@code false} otherwise. A successful
   *         {@link ValidationResult} is always empty. All other methods will not return any resonable result.
   */
  default boolean isValid() {

    return false;
  }

  /**
   * @return the optional {@link Validator#validate(Object, Object) validation source} describing the origin of the
   *         {@code value} that has been validated. May be {@code null}. If present this may be the filename where the
   *         value was read from, an XPath where the value was located in an XML document, the label of a widget of the
   *         UI containing the value, etc. This will help to find the problem easier.
   */
  String getSource();

  /**
   * @return the failure code or {@code null} if {@link #isValid() valid}. For a single {@link ValidationFailure} this
   *         will be the {@link Validator#getId() ID} of the failing {@link Validator}. The code is a stable identifier
   *         that indicates the type of the failure. It can be used for automated testing in order to make the
   *         test-cases independent from the actual message texts so they are maintainable and will not break e.g. if
   *         typos are fixed in the messages.
   * @see ComposedValidationFailure#CODE
   */
  String getCode();

  /**
   * @see #getMessage(boolean)
   */
  @Override
  default String getMessage() {

    return getLocalizedMessage(Locale.ROOT);
  }

  /**
   * @see #getMessage()
   * @param verbose
   * @return the localized message.
   */
  default String getMessage(boolean verbose) {

    return getLocalizedMessage(Locale.ROOT, verbose);
  }

  /**
   * This method gets the resolved and localized message.
   *
   * @param verbose the verbose flag (to include {@link #getCode() code}(s), etc.
   * @return the localized message.
   */
  default String getLocalizedMessage(boolean verbose) {

    return getLocalizedMessage(Locale.getDefault(), verbose);
  }

  /**
   * This method gets the resolved and localized message.
   *
   * @param locale is the {@link Locale} to translate to.
   * @param verbose the verbose flag (to include {@link #getCode() code}(s), etc.
   * @return the localized message.
   */
  default String getLocalizedMessage(Locale locale, boolean verbose) {

    StringBuilder result = new StringBuilder();
    getLocalizedMessage(locale, result, verbose);
    return result.toString();
  }

  /**
   * @see #getLocalizedMessage(Locale, Appendable, boolean)
   */
  @Override
  default void getLocalizedMessage(Locale locale, Appendable buffer) {

    getLocalizedMessage(locale, buffer, false);
  }

  /**
   * @see #getLocalizedMessage(Locale, Appendable)
   * @param locale is the {@link Locale} to translate to.
   * @param buffer the {@link Appendable} where to {@link Appendable#append(CharSequence) write} the message to.
   * @param verbose the verbose flag (to include {@link #getCode() code}(s), etc.
   */
  void getLocalizedMessage(Locale locale, Appendable buffer, boolean verbose);

  /**
   * @param result another {@link ValidationResult} to combine with this one.
   * @return the {@link ValidationResult} composed out of this with the given {@code result}.
   * @see ValidationResultBuilder
   */
  ValidationResult add(ValidationResult result);

  /**
   * @param code the {@link #getCode() code} to check for.
   * @return {@code true} if this {@link ValidationResult} itself {@link #getCode() has} the given {@code code} or
   *         recursively {@link ComposedValidator#getChild(int) contains} such {@link ValidationResult}s, {@code false}
   *         otherwise.
   */
  default boolean containsCode(String code) {

    return (getCode().equals(code));
  }

}
