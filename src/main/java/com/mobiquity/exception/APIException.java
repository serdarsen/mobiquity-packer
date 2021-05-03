package com.mobiquity.exception;

/**
 * An APIException is thrown by an accessor if it has an unexpected problem.
 *
 * @author Serdar ŞEN
 */
public class APIException extends Exception {

  /**
   * Create an APIException with a specific message and cause.
   * @param cause
   */
  public APIException(String message, Exception cause) {
    super(message, cause);
  }

  /**
   * Create an APIException with a specific message.
   * @param message
   */
  public APIException(String message) {
    super(message);
  }
}
