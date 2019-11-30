/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

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
   * This method gets the <em>code</em> of this {@link ValidationResult}. The code is a stable identifier that indicates
   * the type of the failure. It can be used for automated testing in order to make the test-cases independent from the
   * actual message texts so they are maintainable and will not break e.g. if typos are fixed in the messages.
   *
   * @return the failure code.
   */
  String getCode();

}
