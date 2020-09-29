/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.test.string;

import org.junit.jupiter.api.Test;

import io.github.mmm.validation.string.ValidatorEmail;
import io.github.mmm.validation.test.ValidatorTest;

/**
 * Test of {@link ValidatorEmail}.
 */
public class ValidatorEmailTest extends ValidatorTest {

  /** Test valid emails. */
  @Test
  public void testValidEmails() {

    ValidatorEmail validator = ValidatorEmail.get();
    String[] valid = { "john.doe@mail.com", "john-doe@fast.mail.com", "john$doe@very.fast.mail.com",
    "johndoe1@mail.com", "john.doe-@mail.com", "john.doe_@mail.com", "john.doe+@mail.com", "john.doe'@mail.com",
    "john.doe!@mail.com", "john.doe=@mail.com", "\"john.doe.,!+=)(*'\"@mail.com", "\"John M. Doe\"<john.doe@mail.com>",
    "\"John M. Doe\" <  john.doe   @   mail  .    com    >", "john.doe@1.2.3.com", "john.doe@[1.2.3.4]",
    "john.doe@[2001:db8:a::]", "\"John Doe\" <john.doe@räksmörgås.josefßon.org>" };
    checkValid(validator, valid);
  }

  /** Test invalid emails. */
  @Test
  public void testInvalidEmails() {

    ValidatorEmail validator = ValidatorEmail.get();
    String[] invalid = { "@mail.com", "john.doe@", "john.doe@.com", "john.doe.@mail.com", "John Doe john.doe@mail.com",
    "John Doe <john.doe@mail.com>", "\"John M. Doe\" junior <john.doe@mail.com>", "john()doe@mail.com",
    "john.doe@1.2.3.4", "john.doe@[256.1.1.1]", "john.doe@2001:db8:a::", "john.doe@[2001:db8:a::/64]" };
    checkInvalids(validator, "The value has to be a valid email address.", invalid);
  }

}
