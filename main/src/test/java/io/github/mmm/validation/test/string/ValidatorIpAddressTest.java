/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.test.string;

import org.junit.jupiter.api.Test;

import io.github.mmm.validation.string.ValidatorIpAddress;
import io.github.mmm.validation.test.ValidatorTest;

/**
 * Test of {@link ValidatorIpAddress}.
 */
public class ValidatorIpAddressTest extends ValidatorTest {

  /** Test valid IPs. */
  @Test
  public void testValidIpAddresses() {

    ValidatorIpAddress validator = ValidatorIpAddress.get();
    String[] valid = { "127.0.0.1", "[1.2.3.4]", "::", "::1", "[2001:db8:a::]", "2001:0db8:0a0b:12f0:0:0:0:1" };
    checkValid(validator, valid);
  }

  /** Test invalid IPs. */
  @Test
  public void testInvalidIpAddresses() {

    ValidatorIpAddress validator = ValidatorIpAddress.get();
    String[] invalid = { "1", "1.2", "1.2.3", "1.2.3.256", "1.2.3.260", "1.2.3.300", "1.2.3.4.5", "127.0.0.1:443",
    "1.two", ":::", "2001:db8:a::/64", "[2001:db8:a::/64]", "2001:0db8:0a0b:12f0:0:0:0:1:2",
    "2001:0db8:0a0b:12f0:0:0:0::1" };
    checkInvalids(validator, "The value has to be a valid IP-address.", invalid);
  }

}
