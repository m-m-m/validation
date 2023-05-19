/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import io.github.mmm.base.i18n.Localizable;
import io.github.mmm.base.range.Range;
import io.github.mmm.base.range.RangeType;

/**
 * Test of {@link ComposedValidator}.
 */
public class ComposedValidatorTest {

  /** Test of {@link ComposedValidator}. */
  @Test
  public void testComposedValidator() {

    // given
    Pattern regex = Pattern.compile("[a-zA-Z0-9-]*");
    // when
    ValidatorStringPattern pattern = new ValidatorStringPattern(regex);
    ValidatorStringLength length1 = new ValidatorStringLength(0, 10);
    ValidatorStringLength length2 = new ValidatorStringLength(1, 43);
    ComposedValidator<String> composed = new ComposedValidator<>(Validator.none(), pattern, length1, length2);
    // then
    assertThat(composed.getChild(ValidatorStringPattern.class)).isSameAs(pattern);
    Integer min = composed.getMin();
    assertThat(min).isEqualTo(1);
    Integer max = composed.getMax();
    assertThat(max).isEqualTo(10);

    ValidationResult result = composed.validate("0123456789ä");
    assertThat(result.isValid()).isFalse();
    assertThat(result).isInstanceOf(ComposedValidationFailure.class);
    ComposedValidationFailure composedFailure = (ComposedValidationFailure) result;
    assertThat(composedFailure.getChildCount()).isEqualTo(2);
    assertThat(composedFailure.getChild(0).getMessage(false)).isEqualTo("Value does not match pattern " + regex);
    assertThat(composedFailure.getChild(1).getMessage(false))
        .isEqualTo("Length (11) has to be in the range " + length1.getRange());
  }

  @SuppressWarnings("unchecked")
  private static class ValidatorStringLength extends AbstractValueValidator<String> {

    private final Range<Integer> range;

    ValidatorStringLength(int min, int max) {

      super();
      this.range = RangeType.of(min, max);
    }

    @Override
    protected Localizable validateNotNull(String value) {

      int len = value.length();
      if (!this.range.contains(len)) {
        return Localizable.ofStatic("Length (" + len + ") has to be in the range " + this.range);
      }
      return null;
    }

    @Override
    public Range<Integer> getRange() {

      return this.range;
    }

  }

  private static class ValidatorStringPattern extends AbstractValueValidator<String> {

    private final Pattern regex;

    ValidatorStringPattern(Pattern regex) {

      super();
      this.regex = regex;
    }

    @Override
    protected Localizable validateNotNull(String value) {

      if (!this.regex.matcher(value).matches()) {
        return Localizable.ofStatic("Value does not match pattern " + this.regex);
      }
      return null;
    }
  }

}
