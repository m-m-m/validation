/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import java.util.Objects;
import java.util.regex.Pattern;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} {@link Pattern#matcher(CharSequence) matches} a given {@link Pattern}.
 *
 * @since 1.0.0
 */
public class ValidatorPattern extends AbstractValueValidator<CharSequence> {

  /** @see #getCode() */
  public static final String CODE = "Pattern";

  private final Pattern pattern;

  /**
   * The constructor.
   *
   * @param pattern the regular expression {@link Pattern}.
   */
  public ValidatorPattern(Pattern pattern) {

    super();
    this.pattern = pattern;
  }

  /**
   * The constructor.
   *
   * @param pattern the regular expression {@link Pattern#compile(String) pattern}.
   */
  public ValidatorPattern(String pattern) {

    this(Pattern.compile(pattern));
  }

  @Override
  protected String getCode() {

    return CODE;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    boolean matches = this.pattern.matcher(value).matches();
    if (!matches) {
      return NlsBundleValidation.INSTANCE.errorValueFormat(null, this.pattern);
    }
    return null;
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.hashCode(), this.pattern);
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    } else if (!super.equals(obj)) {
      return false;
    }
    ValidatorPattern other = (ValidatorPattern) obj;
    return Objects.equals(this.pattern, other.pattern);
  }

}
