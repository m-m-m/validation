/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.validation;

import java.util.Locale;

/**
 * Result of a {@link Validator#validate(Object) validation}.
 *
 * @since 3.0.0
 */
public interface ValidationResult extends Composable<ValidationResult> {

  /**
   * @return {@code true} if the validation was successful, {@code false} otherwise. A successful
   *         {@link ValidationResult} is always empty. All other methods will not return any resonable result.
   */
  default boolean isValid() {

    return false;
  }

  /**
   * @return the optional {@link Validator#validate(Object, Object) validation source} describing the origin of the
   *         {@code value} that has been validated. May be {@code null}.
   */
  String getSource();

  /**
   * @return the message of the validation failure. Will be {@code null} if {@link #isValid() valid} is {@code true},
   *         otherwise it may not be {@code null} or empty and explain the reason of the failure in a understandable but
   *         short form to end-users. Examples are "Value may not be blank." or "Value has to be in the range from 5 to
   *         9.". Depending on the {@link Validator#validate(Object, Object) usage} the message may also contain
   *         additional context information in order to determine the source of the problem.
   */
  default String getMessage() {

    return getMessage(null);
  }

  /**
   * This method gets the {@link #getMessage() message} localized for the given {@link Locale}. <br>
   * <b>ATTENTION:</b><br>
   * This method is designed for server applications with NLS. On client side (e.g. in the web browser) only a single
   * locale may be supported at a time and this method will behave like {@link #getMessage()} ignoring the
   * {@link Locale}.
   *
   * @param locale is the {@link Locale}.
   * @return the message.
   */
  String getMessage(Locale locale);

  /**
   * This method gets the <em>code</em> of this {@link ValidationResult}. The code is a stable identifier that indicates
   * the type of the failure. It can be used for automated testing in order to make the test-cases independent from the
   * actual message texts so they are maintainable and will not break e.g. if typos are fixed in the messages.
   *
   * @return the failure code.
   */
  String getCode();

}
