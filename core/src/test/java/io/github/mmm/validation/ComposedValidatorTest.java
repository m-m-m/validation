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

  @Test
  public void test() {

    // given
    Pattern regex = Pattern.compile("[a-zA-Z0-9-]*");
    // when
    ValidatorStringPattern pattern = new ValidatorStringPattern(regex);
    ValidatorStringLength length1 = new ValidatorStringLength(0, 42);
    ValidatorStringLength length2 = new ValidatorStringLength(1, 43);
    ComposedValidator<String> composed = new ComposedValidator<>(Validator.none(), pattern, length1, length2);
    // then
    assertThat(composed.getChild(ValidatorStringPattern.class)).isSameAs(pattern);
    Integer min = composed.getMin();
    assertThat(min).isEqualTo(0);
    Integer max = composed.getMax();
    assertThat(max).isEqualTo(43);
  }

  private Validator<String> length(int min, int max) {

    Validator<String> length = new AbstractValueValidator<String>() {
      @Override
      protected Localizable validateNotNull(String value) {

        int len = value.length();
        if ((len > min) || (len < max)) {
          return Localizable.ofStatic("String length (" + len + ") has to be in the range from " + min + " to " + max);
        }
        return null;
      }

      @Override
      public Integer getMin() {

        return Integer.valueOf(min);
      }

      @Override
      public Integer getMax() {

        return Integer.valueOf(max);
      }
    };
    return length;
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
      if ((len > this.min.intValue()) || (len < this.max.intValue())) {
        return Localizable
            .ofStatic("String length (" + len + ") has to be in the range from " + this.min + " to " + this.max);
      }
      return null;
    }

    @Override
    public Integer getMin() {

      return this.min;
    }

    @Override
    public Integer getMax() {

      return this.max;
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
