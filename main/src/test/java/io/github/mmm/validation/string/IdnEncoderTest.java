/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test of {@link IdnEncoder}.
 */
@SuppressWarnings("javadoc")
public class IdnEncoderTest extends Assertions {

  /** Test of {@link IdnEncoder#encode(String)}. */
  @Test
  public void testIdn() {

    IdnEncoder encoder = new IdnEncoder();
    assertThat(encoder.encode("ascii")).isEqualTo("ascii");
    assertThat(encoder.encode("CamlCase")).isEqualTo("camlcase");
    assertThat(encoder.encode("räksmörgås")).isEqualTo("xn--rksmrgs-5wao1o");
    assertThat(encoder.encode("παράδειγμα")).isEqualTo("xn--hxajbheg2az3al");
    assertThat(encoder.encode("пример")).isEqualTo("xn--e1afmkfd");
    assertThat(encoder.encode("бг")).isEqualTo("xn--90ae");
    assertThat(encoder.encode("dömäin")).isEqualTo("xn--dmin-moa0i");
    assertThat(encoder.encode("fußball")).isEqualTo("xn--fuball-cta");
  }

}
