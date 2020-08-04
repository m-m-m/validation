/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

/**
 * Internal helper class for {@link String}s and {@link CharSequence}s.
 *
 * @since 1.0.0
 */
class StringHelper {

  static int indexOfUnescaped(String string, char c, int start) {

    return indexOfUnescaped(string, c, start, string.length());
  }

  static int indexOfUnescaped(String string, char c, int start, int limit) {

    assert (start >= 0);
    assert (limit <= string.length());
    int i = start;
    boolean escape = false;
    while (i < limit) {
      char current = string.charAt(i);
      if (current == '\\') {
        escape = !escape;
      } else {
        if ((current == c) && !escape) {
          return i;
        }
        escape = false;
      }
      i++;
    }
    return -1;
  }

  /**
   * @param host the host to split into segments (aka labels).
   * @return an array with the trimmed segments of the given {@code host}. Will be {@code null} if the host was detected
   *         as invalid (only partial validation).
   */
  static String[] splitHost(String host) {

    int length = host.length();
    int dotIndex = host.indexOf('.');
    if ((dotIndex == 0) || (dotIndex == (length - 1))) {
      return null; // host must not start or end with dot
    }
    if (dotIndex == -1) {
      return new String[] { host };
    }
    int segmentCount = 1;
    int i = dotIndex;
    while (i > 0) {
      segmentCount++;
      i = host.indexOf('.', i + 1);
    }
    String[] segments = new String[segmentCount];
    int start = 0;
    segmentCount = 0;
    while (dotIndex > 0) {
      segments[segmentCount++] = host.substring(start, dotIndex).trim();
      start = dotIndex + 1;
      dotIndex = host.indexOf('.', start);
    }
    segments[segmentCount++] = host.substring(start).trim();
    assert (segmentCount == segments.length);
    return segments;
  }

  static boolean hasBrackets(String string) {

    int length = string.length();
    return ((length > 2) && (string.charAt(0) == '[') && (string.charAt(length - 1)) == ']');
  }

  static String unbracket(String string) {

    if (hasBrackets(string)) {
      return string.substring(1, string.length() - 1);
    }
    return string;
  }

}
