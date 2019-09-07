/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.validation;

/**
 * This is the abstract interface for an object, that can be {@link #validate() validated}.
 *
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 7.4.0
 */
public abstract interface Validatable {

  /**
   * This method performs the actual validation.
   *
   * @see Validator#validate(Object)
   *
   * @return {@link ValidationResult} the validation failure.
   */
  ValidationResult validate();

}
