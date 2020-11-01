/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.test.string;

import org.junit.jupiter.api.Test;

import io.github.mmm.validation.string.ValidatorHostName;
import io.github.mmm.validation.test.ValidatorTest;

/**
 * Test of {@link ValidatorHostName}.
 */
public class ValidatorHostNameTest extends ValidatorTest {

  /** Test valid host names. */
  @Test
  public void testValidHostNames() {

    ValidatorHostName validator = ValidatorHostName.get();
    String[] valid = { "mail.com", "fast.mail.com", "very.fast.mail.com", "räksmörgås.josefßon.org" };
    checkValid(validator, valid);
  }

  /** Test invalid host names. */
  @Test
  public void testInvalidHostNames() {

    ValidatorHostName validator = ValidatorHostName.get();
    String[] invalid = { "@mail.com", ".com" };
    checkInvalids(validator, "The value has to be a valid hostname.", invalid);
  }

}
