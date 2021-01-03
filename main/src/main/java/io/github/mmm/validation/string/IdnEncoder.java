/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

/**
 * Partial implementation of punycode to encode IDN to ACE. It is stateful and therefore <b>not</b> thread-safe.
 */
final class IdnEncoder {

  private static final String XN_PREFIX = "xn--";

  private static final int MAX_LABEL_LENGTH = 63;

  private static final int BASE = 36;

  private static final int BASIC_LIMIT = 0x80;

  private int n;

  private int delta;

  private int bias;

  private StringBuilder output;

  private int cpCount;

  private int cpProcessed;

  private int[] cpBuffer;

  private boolean cpFirst;

  /**
   * The constructor.
   */
  public IdnEncoder() {

    super();
    this.output = new StringBuilder(MAX_LABEL_LENGTH);
    this.cpBuffer = new int[MAX_LABEL_LENGTH];
  }

  public String encode(String input) {

    int len = input.length();
    if (len > MAX_LABEL_LENGTH) {
      return null;
    }
    this.output.setLength(0);
    this.cpCount = 0;
    this.cpProcessed = 0;
    this.cpFirst = true;
    this.delta = 0;
    this.n = BASIC_LIMIT;
    this.bias = 72;
    boolean allAscii = true;
    for (int i = 0; i < len; i++) {
      char c = input.charAt(i);
      if (isBasic(c)) {
        this.output.append(toAsciiLowerCase(c));
        this.cpBuffer[this.cpCount] = 0;
        this.cpProcessed++;
      } else {
        if (allAscii && input.startsWith(XN_PREFIX)) { // first non compliant char
          return null;
        }
        allAscii = false;
        int cp = c;
        if (isSurrogate(c)) {
          boolean foundSurrogatePair = false;
          if (isLeadSurrogate(c) && (i < len)) {
            char tail = input.charAt(i++);
            if (isTailSurrogate(tail)) {
              cp = getRawSupplementary(c, tail);
              foundSurrogatePair = true;
            }
          }
          if (!foundSurrogatePair) {
            throw new IllegalArgumentException("Invalid character 0x" + Integer.toHexString(c));
          }
        }
        this.cpBuffer[this.cpCount] = cp;
      }
      this.cpCount++;
    }
    if (allAscii) {
      return this.output.toString();
    }
    if (this.output.length() == 0) {
      this.output.append(XN_PREFIX);
    } else {
      this.output.insert(0, XN_PREFIX);
      this.output.append('-');
    }
    while (this.cpProcessed < this.cpCount) {
      int m = findNextCodePoint();
      this.delta = this.delta + (m - this.n) * (this.cpProcessed + 1);
      this.n = m;
      encodeCodePoints();
      this.delta++;
      this.n++;
    }
    return this.output.toString();
  }

  private static boolean isBasic(char c) {

    return (c < BASIC_LIMIT);
    // if (c >= 'a' && c <= 'z') {
    // return true;
    // } else if (c >= '0' && c <= '9') {
    // return true;
    // } else if (c == '-') {
    // return true;
    // }
    // return false;
  }

  private static char toAsciiLowerCase(char c) {

    if ((c >= 'A') && (c <= 'Z')) {
      return (char) (c + ('a' - 'A'));
    }
    return c;
  }

  private static int getRawSupplementary(char lead, char trail) {

    return (lead << 10) + trail - 56613888;
  }

  private static boolean isLeadSurrogate(char c) {

    return (c & 0xFFFFFC00) == 0xD800;
  }

  private static boolean isTailSurrogate(char c) {

    return (c & 0xFFFFFC00) == 0xDC00;
  }

  private static boolean isSurrogate(char c) {

    return (c & 0xFFFFF800) == 0xD800;
  }

  private int adaptBias() {

    if (this.cpFirst) {
      this.cpFirst = false;
      this.delta = this.delta / 700;
    } else {
      this.delta = this.delta / 2;
    }
    this.delta += this.delta / (this.cpProcessed + 1);
    int count = 0;
    while (this.delta > 455) {
      this.delta = this.delta / 35;
      count = count + 36;
    }
    return count + ((36 * this.delta) / (this.delta + 38));
  }

  /** Find minimum code point greater or equal to {@code n}. */
  private int findNextCodePoint() {

    int m = Integer.MAX_VALUE;
    for (int i = 0; i < this.cpCount; i++) {
      int cp = this.cpBuffer[i];
      if ((cp >= this.n) && (cp < m)) {
        m = cp;
      }
    }
    return m;
  }

  private void encodeCodePoints() {

    for (int i = 0; i < this.cpCount; i++) {
      int cp = this.cpBuffer[i];
      if (cp < this.n) {
        this.delta++;
      } else if (cp == this.n) {
        int q = this.delta;
        int k = BASE;
        while (true) {
          int t = k - this.bias;
          if (t < 1) {
            t = 1;
          } else if (k >= (this.bias + 26)) {
            t = 26;
          }
          if (q < t) {
            break;
          }
          char out = digitToBasic(t + (q - t) % (BASE - t));
          this.output.append(out);
          q = (q - t) / (BASE - t);
        }
        char out = digitToBasic(q);
        this.output.append(out);
        this.bias = adaptBias();
        this.delta = 0;
        this.cpProcessed++;
      }
    }
  }

  private static char digitToBasic(int digit) {

    if (digit < 26) {
      return (char) ('a' + digit);
    } else {
      return (char) ('0' + (digit - 26));
    }
  }

}
