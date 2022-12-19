package com.fliurkevych.pdp.pdpspringcore.exception;

/**
 * @author Oleh Fliurkevych
 */
public class ValidationException extends RuntimeException {

  public ValidationException(String message, Object... objects) {
    super(String.format(message, objects));
  }
}
