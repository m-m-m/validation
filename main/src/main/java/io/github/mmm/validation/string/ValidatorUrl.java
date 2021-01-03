/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.string;

import io.github.mmm.nls.NlsMessage;
import io.github.mmm.validation.AbstractValueValidator;
import io.github.mmm.validation.main.NlsBundleValidation;

/**
 * {@link io.github.mmm.validation.Validator} {@link #validate(CharSequence, Object) validating} that a given
 * {@link CharSequence} is a valid URL.
 *
 * @since 1.0.0
 */
public final class ValidatorUrl extends AbstractValueValidator<CharSequence> {

  /** @see #get() */
  private static final ValidatorUrl INSTANCE = new ValidatorUrl();

  /** @see #getId() */
  public static final String ID = "URL";

  /**
   * The constructor.
   */
  private ValidatorUrl() {

    super();
  }

  @Override
  public String getId() {

    return ID;
  }

  @Override
  protected NlsMessage validateNotNull(CharSequence value) {

    if (isValidUrl(value.toString().trim())) {
      return null;
    }
    return NlsBundleValidation.INSTANCE.errorNoUrl(value);
  }

  static boolean isValidUrl(String value) {

    String protocol = "http";
    int length = value.length();
    int protocolIndex = value.indexOf(':');
    if (protocolIndex >= 0) {
      protocol = value.substring(0, protocolIndex);
      protocolIndex++;
      int slashes = 0;
      while ((slashes < 2) && (protocolIndex <= length) && (value.charAt(protocolIndex) == '/')) {
        protocolIndex++;
        slashes++;
      }
    } else {
      protocolIndex = 0;
    }
    if (!isValidProtocol(protocol)) {
      return false;
    }
    int userIndex = value.indexOf('@', protocolIndex);
    if (userIndex < 0) {
      userIndex = protocolIndex;
    } else {
      String userInfo = value.substring(protocolIndex, userIndex);
      if (!isValidUserInfo(userInfo)) {
        return false;
      }
    }
    int pathIndex = value.indexOf('/', userIndex);
    if (pathIndex >= 0) {
      int queryIndex = value.indexOf('?', pathIndex);
      if (queryIndex < 0) {
        queryIndex = length;
      } else {
        String query = value.substring(queryIndex);
        if (!isValidQuery(query)) {
          return false;
        }
      }
      String path = value.substring(pathIndex, queryIndex);
      if (!isValidPath(path)) {
        return false;
      }
    } else {
      pathIndex = length;
    }
    int portIndex = value.indexOf(':', userIndex);
    if (portIndex < 0) {
      portIndex = pathIndex;
    } else {
      String port = value.substring(portIndex, pathIndex);
      if (!isValidPort(port)) {
        // colon can be part of IPv6
        portIndex = pathIndex;
      }
    }
    String host = value.substring(userIndex, portIndex);
    if (!ValidatorHost.isValidHost(host)) {
      return false;
    }
    return true;
  }

  private static boolean isValidProtocol(String protocol) {

    return true;
  }

  /**
   * @param userInfo the user-info (login:password).
   */
  private static boolean isValidUserInfo(String userInfo) {

    return true;
  }

  private static boolean isValidPort(String port) {

    int length = port.length();
    if ((length == 0) || (length > 5)) {
      return false;
    }
    int portNumber = 0;
    int leadingZeros = 0;
    for (int i = 0; i < length; i++) {
      char c = port.charAt(i);
      if (c < '0') {
        return false;
      } else if (c > '9') {
        return false;
      } else if (c == '0') {
        if (leadingZeros >= 0) {
          leadingZeros++;
        }
      } else {
        portNumber = (portNumber * 10) + (c - '0');
        if (leadingZeros >= 0) {
          if ((length - leadingZeros) > 5) {
            return false;
          }
          leadingZeros = -1;
        }
      }
    }
    // maximum port is 65535 (practically already 20000 due to ephemeral port clashing)
    if (portNumber > 65535) {
      return false;
    }
    return true;
  }

  private static boolean isValidPath(String path) {

    return true;
  }

  private static boolean isValidQuery(String path) {

    return true;
  }

  /**
   * @return the instance of {@link ValidatorUrl}.
   */
  public static ValidatorUrl get() {

    return INSTANCE;
  }

}
