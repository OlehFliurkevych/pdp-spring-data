package com.fliurkevych.pdp.pdpspringcore.util;

import com.fliurkevych.pdp.pdpspringcore.exception.ValidationException;
import com.fliurkevych.pdp.pdpspringcore.model.Event;
import com.fliurkevych.pdp.pdpspringcore.model.UserAccount;

import java.math.BigDecimal;

/**
 * @author Oleh Fliurkevych
 */
public final class ValidationUtils {

  public static void validatePlaceNumber(int place) {
    if (place > 100 || place <= 0) {
      throw new ValidationException("Entered incorrect place number");
    }
  }

  public static void validateUserBalance(UserAccount userAccount, Event event) {
    var balance = userAccount.getBalance();
    if (balance.equals(BigDecimal.ZERO)) {
      throw new ValidationException("Balance of user account is empty");
    }
    if (balance.compareTo(event.getTicketPrice()) < 0) {
      throw new ValidationException("User don't have enough money");
    }
  }

}
