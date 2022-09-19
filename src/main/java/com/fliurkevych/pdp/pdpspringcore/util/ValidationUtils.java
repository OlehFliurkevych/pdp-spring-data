package com.fliurkevych.pdp.pdpspringcore.util;

import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;

/**
 * @author Oleh Fliurkevych
 */
public final class ValidationUtils {

  public static void validatePlaceNumber(int place) {
    if (place > 100 || place <= 0) {
      throw new ValidationException("Entered incorrect place number");
    }
  }

}
